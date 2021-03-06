package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rainbow.bean.GetJobsResponse;
import com.example.rainbow.databinding.LvItemSkglBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class CollectionManagementAdapter extends BaseListAdapter {
    public CollectionManagementAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {
        GetJobsResponse.DataBean.ItemsBean bean = (GetJobsResponse.DataBean.ItemsBean) datas.get(position);

        LvItemSkglBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setBean(bean);

        return binding.getRoot();
    }
}
