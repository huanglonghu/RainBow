package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.databinding.FragmentFaultRecordBinding;
import com.example.rainbow.ui.adapter.FaultRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class FaultRecord extends BaseFragment {


    private FragmentFaultRecordBinding binding;
    private Machine machine;
    private ArrayList<MachineDetailResponse.DataBean.MachineFaultsBean> datas;
    private FaultRecordAdapter faultRecordAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        machine = (Machine) getParentFragment();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fault_record, container, false);
        initView();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    private int machineId;

    public void setData(List<MachineDetailResponse.DataBean.MachineFaultsBean> machineFaults, int machineId) {
        this.machineId = machineId;
        if (machineFaults != null) {
            datas.addAll(machineFaults);
            faultRecordAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        faultRecordAdapter = new FaultRecordAdapter(getContext(), datas, R.layout.lv_item_fault_record);
        binding.lvFaultRecord.setAdapter(faultRecordAdapter);
    }

    @Override
    public void initlisten() {


        binding.lvFaultRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FaultDetail faultDetail = new FaultDetail();
                String title = getContext().getString(R.string.faultDetail);
                Bundle bundle = new Bundle();
                MachineDetailResponse.DataBean.MachineFaultsBean bean = datas.get(position);
                bundle.putInt("id", bean.getId());
                faultDetail.setArguments(bundle);
                machine.toggle("faultDetail", faultDetail, " > " + title);
            }
        });

    }
}
