package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.GetJobDetailResponse;
import com.example.rainbow.catche.Loader.RxImageLoader;
import com.example.rainbow.databinding.LvItemLineBinding;
import com.example.rainbow.util.ImagUtil;

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
        if (position == 0) {
            binding.ivSign.setBackgroundResource(R.drawable.line_sign2);
        } else {
            binding.ivSign.setBackgroundResource(R.drawable.line_sign1);
        }
        if (bean.isIsSignIn()) {
            binding.ivSign.setSelected(true);
        }

        String image = bean.getImage();
        String url = ImagUtil.handleUrl(image);
        if (!TextUtils.isEmpty(url)) {
            RxImageLoader.with(context).load(url).into(binding.iv, 1);
        }

        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance().startNaviGoogle(23.1066805, 113.3245904);
            }
        });
        return binding.getRoot();
    }
}
