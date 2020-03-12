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
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.LoginResponse;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentLoginBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.util.GsonUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class Login extends BaseFragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        initView();
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

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.userName.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = binding.pwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtil.getInstance().login(userName, pwd).subscribe(
                        str -> {
                            LoginResponse loginResponse = GsonUtil.fromJson(str, LoginResponse.class);
                            UserBean userBean = new UserBean();
                            userBean.setToken(loginResponse.getData().getToken());
                            userBean.setUserType(loginResponse.getData().getUserType());
                            userBean.setNickName(loginResponse.getData().getUserName());
                            UserOption.getInstance().addUser(userBean);
                            Presenter.getInstance().step2MainFragment("main");
                        }
                );

            }
        });


    }
}
