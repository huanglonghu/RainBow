package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.databinding.FragmentNoticeDetailBinding;
import com.example.rainbow.net.HttpUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;



public class NoticeDetail extends BaseFragment {

    private FragmentNoticeDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_detail, container, false);
        initView();
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            int id = bundle.getInt("id");
            HttpUtil.getInstance().getNoticeDetailById(id).subscribe(
                    str -> {

                    }
            );
        }
    }

    @Override
    public void initView() {



    }

    @Override
    public void initlisten() {

    }
}
