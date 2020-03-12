package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.databinding.LvItemFaultRecordBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class FaultRecordAdapter extends BaseListAdapter {
    public FaultRecordAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {
        MachineDetailResponse.DataBean.MachineFaultsBean bean = (MachineDetailResponse.DataBean.MachineFaultsBean) datas.get(position);
        LvItemFaultRecordBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setBean(bean);
        return binding.getRoot();
    }
}
