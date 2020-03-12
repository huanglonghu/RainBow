package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.EnterBody;
import com.example.rainbow.bean.QuerryMachineResponse;
import com.example.rainbow.bean.XFRecord;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentWinlostRecordBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.WinLostListAdapter;
import com.example.rainbow.util.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class WinLostRecord extends BaseFragment {


    private FragmentWinlostRecordBinding binding;
    private UserBean userBean;
    private ArrayList<XFRecord.DataBean.ItemsBean> datas;
    private WinLostListAdapter winLostListAdapter;
    private ArrayList<String> machines;
    private ArrayAdapter adapter;
    private EnterBody enterBody;
    private HashMap<String, Integer> machineMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_winlost_record, container, false);
        userBean = UserOption.getInstance().querryUser();
        initView();
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        HttpUtil.getInstance().querryXFRecord(1).subscribe(
                str -> {
                    XFRecord xfRecord = GsonUtil.fromJson(str, XFRecord.class);
                    XFRecord.DataBean data = xfRecord.getData();
                    List<XFRecord.DataBean.ItemsBean> items = data.getItems();
                    datas.addAll(items);
                    winLostListAdapter.notifyDataSetChanged();
                }
        );
        machineMap = new HashMap<>();
        HttpUtil.getInstance().querryMachines().subscribe(
                str -> {
                    QuerryMachineResponse querryMachineResponse = GsonUtil.fromJson(str, QuerryMachineResponse.class);
                    QuerryMachineResponse.DataBean data = querryMachineResponse.getData();
                    List<QuerryMachineResponse.DataBean.ItemsBean> items = data.getItems();
                    for (int i = 0; i < items.size(); i++) {
                        QuerryMachineResponse.DataBean.ItemsBean bean = items.get(i);
                        machines.add(bean.getMachineName());
                        machineMap.put(bean.getMachineName(), bean.getId());
                    }
                    adapter.notifyDataSetChanged();
                }
        );

    }

    @Override
    public void initView() {

        datas = new ArrayList<>();
        winLostListAdapter = new WinLostListAdapter(getContext(), datas, R.layout.lv_item_winlost);
        binding.lvWinlost.setAdapter(winLostListAdapter);
        machines = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, machines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spMachine.setAdapter(adapter);
        binding.spMachine.setOnItemSelectedListener(new SpinnerSelectedListener());
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void initlisten() {
        enterBody = new EnterBody();
        enterBody.setUserId((int) userBean.getId());
        enterBody.setMachineId(1);
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = (String) binding.spMachine.getSelectedItem();
                Integer id = machineMap.get(selectedItem);
                enterBody.setMachineId(id);
                String xf = binding.etXf.getText().toString();
                if (TextUtils.isEmpty(xf)) {
                    Toast.makeText(getContext(), "请输入本店总洗分", Toast.LENGTH_SHORT).show();
                    return;
                }
                enterBody.setLossMoney(Integer.parseInt(xf));
                HttpUtil.getInstance().enterXf(enterBody).subscribe(
                        str -> {
                            Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                        }
                );

            }
        });


    }
}