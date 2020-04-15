package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.databinding.FragmentHistoryRecordBinding;
import com.example.rainbow.ui.adapter.HistoryRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class HistoryRecord extends BaseFragment {

    private FragmentHistoryRecordBinding binding;
    private ArrayList<MachineDetailResponse.DataBean.MachineHistoryProfitLossBean> datas;
    private HistoryRecordAdapter historyRecordAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history_record, container, false);
        initView();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }


    public void setData(List<MachineDetailResponse.DataBean.MachineHistoryProfitLossBean> machineHistoryProfitLoss) {
        if (machineHistoryProfitLoss != null) {
            datas.addAll(machineHistoryProfitLoss);
            historyRecordAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        historyRecordAdapter = new HistoryRecordAdapter(getContext(), datas, R.layout.lv_item_history_record);
        binding.lvHistoryRecord.setAdapter(historyRecordAdapter);
    }

    @Override
    public void initlisten() {


    }
}
