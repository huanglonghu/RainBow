package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.ClockInRecordResponse;
import com.example.rainbow.databinding.LvItemClockinBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class ClockInListAdapter extends BaseListAdapter {
    public ClockInListAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {


        LvItemClockinBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        ClockInRecordResponse.DataBean.ItemsBean bean = (ClockInRecordResponse.DataBean.ItemsBean) datas.get(position);
        binding.setBean(bean);
        return binding.getRoot();
    }
}
