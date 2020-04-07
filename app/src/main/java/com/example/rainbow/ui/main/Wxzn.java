package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.databinding.FragmentNoticeBinding;
import com.example.rainbow.databinding.FragmentWxznBinding;
import com.example.rainbow.ui.fragment.NoticeList;
import com.example.rainbow.ui.fragment.PersonalData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class Wxzn extends BaseFragment {

    private FragmentWxznBinding binding;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wxzn, container, false);
        binding.setWxzn(this);
        fm = getChildFragmentManager();
        initView();
        return binding.getRoot();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {

        WxznList wxznList = new WxznList();
        step2wxzn("wxznList", wxznList);

    }

    public void step2wxzn(String name, BaseFragment fragment) {
        fm.beginTransaction().replace(R.id.wxzn_container, fragment).addToBackStack(name).commit();
        if (fragment instanceof WxznList) {
            binding.back.setVisibility(View.GONE);
        } else {
            binding.back.setVisibility(View.VISIBLE);
        }
    }

    public void hideBack() {
        binding.back.setVisibility(View.GONE);
    }


    @Override
    public void initlisten() {

    }

    public void back() {
        BaseFragment fragment = (BaseFragment) fm.findFragmentById(R.id.wxzn_container);
        if (!(fragment instanceof WxznList)) {
            fm.popBackStack();
        }
    }
}
