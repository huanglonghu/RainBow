package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.MachineRoomUser;
import com.example.rainbow.bean.RouteUser;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentPersonalDataBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.main.PersonDetail;
import com.example.rainbow.util.GsonUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class PersonalData extends BaseFragment {

    private FragmentPersonalDataBinding binding;
    private PersonDetail personDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        personDetail = (PersonDetail) getParentFragment();
        personDetail.hideBack();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_data, container, false);
        binding.setPresenter(Presenter.getInstance());
        initData();
        initlisten();
        return binding.getRoot();
    }


    @Override
    public void initData() {
        UserBean userBean = UserOption.getInstance().querryUser();
        if (userBean != null) {
            if (userBean.getUserType() == 2) {
                HttpUtil.getInstance().getMachineRoomUserMsg().subscribe(
                        str -> {
                            MachineRoomUser machineRoomUser = GsonUtil.fromJson(str, MachineRoomUser.class);
                            MachineRoomUser.DataBean data = machineRoomUser.getData();
                            userBean.setMobile(data.getMobile());
                            userBean.setShopName(data.getShopName());
                            userBean.setShopId(data.getShopId());
                            binding.setUserBean(userBean);
                        }
                );
            } else {
                HttpUtil.getInstance().getLxUserMsg().subscribe(
                        str -> {
                            RouteUser routeUser = GsonUtil.fromJson(str, RouteUser.class);
                            RouteUser.DataBean data = routeUser.getData();
                            userBean.setMobile(data.getMobile());
                            binding.setUserBean(userBean);
                        }
                );
            }
        }

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {


        binding.editPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPwd editPwd = new EditPwd();
                String title = getContext().getString(R.string.editPwd);
                personDetail.step2PersonDetail("editPwd", editPwd," > "+title);
            }
        });

    }
}
