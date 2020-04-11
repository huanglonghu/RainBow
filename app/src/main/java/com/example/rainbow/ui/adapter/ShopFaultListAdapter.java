package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.ShopFaultResponse;
import com.example.rainbow.databinding.FragmentShop2Binding;
import com.example.rainbow.databinding.LvItemShopFaultBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class ShopFaultListAdapter extends BaseListAdapter {
    public ShopFaultListAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {

        LvItemShopFaultBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        ShopFaultResponse.DataBean.MachineFaultBean bean = (ShopFaultResponse.DataBean.MachineFaultBean) datas.get(position);
        binding.setData(bean);
        return binding.getRoot();
    }
}
