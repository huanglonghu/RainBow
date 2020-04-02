package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.ShopDetailResponse;
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
        LvItemShopBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        ShopDetailResponse.DataBean.MachineProfitLossBean bean = (ShopDetailResponse.DataBean.MachineProfitLossBean) datas.get(position);
        binding.setData(bean);
        binding.setType(type);
        return binding.getRoot();
    }
}
