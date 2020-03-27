package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.NoticeListResponse;
import com.example.rainbow.databinding.FragmentNoticeDetailBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.util.GsonUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class WxznDetail extends BaseFragment {

    private FragmentNoticeDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void initData() {

        int id = getArguments().getInt("id");
        HttpUtil.getInstance().getWxznDetailById(id).subscribe(
                str -> {
                    NoticeListResponse noticeListResponse = GsonUtil.fromJson(str, NoticeListResponse.class);
                    List<NoticeListResponse.DataBean.ItemsBean> items = noticeListResponse.getData().getItems();
                    NoticeListResponse.DataBean.ItemsBean bean = items.get(0);
                }
        );

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

    }
}
