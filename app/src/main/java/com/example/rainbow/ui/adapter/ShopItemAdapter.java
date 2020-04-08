package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.ShopDetailResponse;
import com.example.rainbow.databinding.LvItemShop1Binding;
import com.example.rainbow.databinding.LvItemShopBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class ShopItemAdapter extends BaseListAdapter {

    public int type;

    public ShopItemAdapter(Context context, List datas, int res, int type) {
        super(context, datas, res);
        this.type = type;
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {
        View view=null;
        ShopDetailResponse.DataBean.MachineProfitLossBean bean = (ShopDetailResponse.DataBean.MachineProfitLossBean) datas.get(position);
        if (type == 1) {
            LvItemShopBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
            binding.setData(bean);
            view=binding.getRoot();
        } else {
            LvItemShop1Binding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
            binding.setData(bean);
            view=binding.getRoot();
        }
        return view;
    }
}
