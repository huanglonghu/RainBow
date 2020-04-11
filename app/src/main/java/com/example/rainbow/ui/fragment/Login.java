package com.example.rainbow.ui.fragment;

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
import com.example.rainbow.constant.HttpParam;
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
        UserBean userBean = UserOption.getInstance().querryUser();
        if (userBean != null) {
            binding.cb.setSelected(userBean.getIsRemember());
            if (userBean.getIsRemember()) {
                binding.userName.setText(userBean.getUserName());
                binding.pwd.setText(userBean.getPwd());
            }
        }
        binding.jzmmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = binding.cb.isSelected();
                binding.cb.setSelected(!selected);
            }
        });
    }

    @Override
    public void initlisten() {

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.userName.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    String toastStr = getString(R.string.qsryhm);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = binding.pwd.getText().toString();

                if (TextUtils.isEmpty(pwd)) {
                    String toastStr2 = getString(R.string.qsrmm);
                    Toast.makeText(getContext(), toastStr2, Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtil.getInstance().login(userName, pwd).subscribe(
                        str -> {
                            LoginResponse loginResponse = GsonUtil.fromJson(str, LoginResponse.class);
                            LoginResponse.DataBean data = loginResponse.getData();
                            UserBean userBean = new UserBean();
                            userBean.setIsRemember(binding.cb.isSelected());
                            userBean.setPwd(pwd);
                            String token = data.getToken();
                            userBean.setToken(token);
                            HttpParam.token = token;
                            userBean.setUserType(data.getUserType());
                            userBean.setUserName(userName);
                            userBean.setNickName(data.getUserName());
                            UserOption.getInstance().addUser(userBean);
                            MainFragment mainFragment = new MainFragment();
                            Presenter.getInstance().step2MainFragment("main",mainFragment);

                            //userType 3路线员工 2机室员工
                        }
                );

            }
        });


    }
}
