package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.PartResponse;
import com.example.rainbow.databinding.LvItemPartBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class PartMenuAdapter extends BaseListAdapter {

    public PartMenuAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {

        PartResponse.DataBean.ItemsBean itemsBean = (PartResponse.DataBean.ItemsBean) datas.get(position);
        LvItemPartBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setItemBean(itemsBean);
        return binding.getRoot();
    }
}
