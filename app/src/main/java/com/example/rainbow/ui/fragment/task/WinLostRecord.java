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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

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
        getXfRecord();
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

    private void getXfRecord() {
        HttpUtil.getInstance().querryXFRecord(page).subscribe(
                str -> {

                    XFRecord xfRecord = GsonUtil.fromJson(str, XFRecord.class);
                    XFRecord.DataBean data = xfRecord.getData();
                    List<XFRecord.DataBean.ItemsBean> items = data.getItems();
                    binding.refreshLayout.finishLoadMore();
                    if (items != null && items.size() > 0) {
                        datas.addAll(items);
                        winLostListAdapter.notifyDataSetChanged();
                        page++;
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false);
                    }

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

    private int page = 1;

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
                String toastStr = getString(R.string.hint3);
                if (TextUtils.isEmpty(xf)) {
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                enterBody.setLossMoney(Integer.parseInt(xf));
                String toastStr2 = getString(R.string.toastStr5);
                HttpUtil.getInstance().enterXf(enterBody).subscribe(
                        str -> {
                            Toast.makeText(getContext(), toastStr2, Toast.LENGTH_SHORT).show();
                        }
                );

            }
        });

        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getXfRecord();
            }
        });


    }
}
