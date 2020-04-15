package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.catche.Loader.RxImageLoader;
import com.example.rainbow.databinding.LvItemHistoryRecordBinding;
import com.example.rainbow.ui.widget.BigImg;
import com.example.rainbow.ui.widget.PhotoGraphWindow;
import com.example.rainbow.util.ImagUtil;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class HistoryRecordAdapter extends BaseListAdapter {
    public HistoryRecordAdapter(Context context, List datas, int res) {
        super(context, datas, res);
    }

    @Override
    protected View initView(LayoutInflater layoutInflater, int res, List datas, int position, ViewGroup parent) {
        MachineDetailResponse.DataBean.MachineHistoryProfitLossBean bean = (MachineDetailResponse.DataBean.MachineHistoryProfitLossBean) datas.get(position);
        LvItemHistoryRecordBinding binding = DataBindingUtil.inflate(layoutInflater, res, parent, false);
        binding.setBean(bean);
        String numberImage = bean.getNumberImage();
        String url = ImagUtil.handleUrl(numberImage);
        if (!TextUtils.isEmpty(url)) {
            RxImageLoader.with(context).load(url).into(binding.iv, 1);
        }
        String[] pathArray = new String[2];
        pathArray[0] = bean.getCodeImage();
        pathArray[1] = bean.getNumberImage();
        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoGraphWindow photoGraphWindow = new PhotoGraphWindow(context, pathArray);
                photoGraphWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            }
        });


        return binding.getRoot();
    }
}
