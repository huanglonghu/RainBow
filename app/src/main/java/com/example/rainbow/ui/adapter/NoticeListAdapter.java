package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.NoticeListResponse;
import com.example.rainbow.databinding.LvItemNoticeBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class NoticeListAdapter extends BaseListAdapter {

    public NoticeListAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {


        LvItemNoticeBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        NoticeListResponse.DataBean.ItemsBean bean = (NoticeListResponse.DataBean.ItemsBean) datas.get(position);
        binding.setBean(bean);
        return binding.getRoot();
    }
}
