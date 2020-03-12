package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.databinding.FragmentNoticeBinding;
import com.example.rainbow.ui.fragment.NoticeList;
import com.example.rainbow.ui.fragment.PersonalData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class Notice extends BaseFragment {

    private FragmentNoticeBinding binding;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false);
        binding.setNotice(this);
        fm = getChildFragmentManager();
        initView();
        return binding.getRoot();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {

        NoticeList noticeList = new NoticeList();
        step2NoticeDetail("noticeList", noticeList);

    }

    public void step2NoticeDetail(String name, BaseFragment fragment) {
        fm.beginTransaction().replace(R.id.notice_container, fragment).addToBackStack(name).commit();
    }

    public void hideBack() {
        binding.back.setVisibility(View.GONE);
    }


    @Override
    public void initlisten() {

    }

    public void back() {
        BaseFragment fragment = (BaseFragment) fm.findFragmentById(R.id.personDetailContainer);
        if (!(fragment instanceof PersonalData)) {
            fm.popBackStack();
        }
    }
}
