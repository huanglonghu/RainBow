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

import java.math.BigDecimal;
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
            binding.ivSign.setImageResource(R.drawable.line_sign2);
        } else {
            binding.ivSign.setImageResource(R.drawable.line_sign1);
        }

        if(bean.isIsSettled()){
            binding.ivSign.setImageLevel(3);
        }else {
            if (bean.isIsSignIn()) {
                binding.ivSign.setImageLevel(2);
            }else {
                binding.ivSign.setImageLevel(1);
            }
        }


        String image = bean.getImage();
        String url = ImagUtil.handleUrl(image);
        if (!TextUtils.isEmpty(url)) {
            RxImageLoader.with(context).load(url).into(binding.iv, 1);
        }


        String coordinate = bean.getCoordinate();
        if (!TextUtils.isEmpty(coordinate)) {
            String[] split = coordinate.split(",");
            double la = Double.parseDouble(split[0]);
            double lon = Double.parseDouble(split[1]);
            binding.line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Presenter.getInstance().startNaviGoogle(la, lon);
                }
            });
        }
        return binding.getRoot();
    }
}
