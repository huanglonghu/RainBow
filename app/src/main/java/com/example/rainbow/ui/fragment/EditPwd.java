package com.example.rainbow.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentEditpwdBinding;
import com.example.rainbow.net.HttpUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class EditPwd extends BaseFragment {

    private FragmentEditpwdBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editpwd, container, false);
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

        UserBean userBean = UserOption.getInstance().querryUser();
        binding.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPwd = binding.oldPwd.getText().toString();
                if (TextUtils.isEmpty(oldPwd)) {
                    String toastStr = getString(R.string.toastStr6);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPwd = binding.newPwd.getText().toString();
                if (TextUtils.isEmpty(newPwd)) {
                    String toastStr = getString(R.string.toastStr7);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String surePwd = binding.surePwd.getText().toString();
                if (TextUtils.isEmpty(surePwd)) {
                    String toastStr = getString(R.string.toastStr19);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!surePwd.endsWith(newPwd)) {
                        String toastStr = getString(R.string.toastStr18);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (userBean.getUserType() == 2) {
                    HttpUtil.getInstance().editeditMachineRoomUserPwd(oldPwd, newPwd).subscribe(
                            str -> {
                                String toastStr = getString(R.string.toastStr9);
                                Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            }
                    );
                } else if (userBean.getUserType() == 3) {
                    HttpUtil.getInstance().editRouterPwd(oldPwd, newPwd).subscribe(
                            str -> {
                                String toastStr = getString(R.string.toastStr9);
                                Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            }
                    );
                }
            }
        });

    }
}
