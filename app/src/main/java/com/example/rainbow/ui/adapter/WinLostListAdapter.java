package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.QuerryMachineResponse;
import com.example.rainbow.bean.XFRecord;
import com.example.rainbow.databinding.LvItemWinlostBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class WinLostListAdapter extends BaseListAdapter {

    public WinLostListAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {

        XFRecord.DataBean.ItemsBean bean = (XFRecord.DataBean.ItemsBean) datas.get(position);
        LvItemWinlostBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setBean(bean);
        return binding.getRoot();
    }
}
