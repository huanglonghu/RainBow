package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.GetJobDetailResponse;
import com.example.rainbow.databinding.LvItemLineBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class SelectLineAdapter extends BaseListAdapter {

    public SelectLineAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {
        GetJobDetailResponse.DataBean.ShopsBean bean = (GetJobDetailResponse.DataBean.ShopsBean) datas.get(position);
        LvItemLineBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setBean(bean);
        if(position==0){
            binding.ivSign.setBackgroundResource(R.drawable.line_sign2);
        }else {
            binding.ivSign.setBackgroundResource(R.drawable.line_sign1);
        }
        if(bean.isIsSignIn()){
            binding.ivSign.setSelected(true);
        }
        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Presenter.getInstance().startNaviGoogle();
            }
        });
        return binding.getRoot();
    }
}
