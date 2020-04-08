package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentTaskSelectBinding;
import com.example.rainbow.ui.main.Task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class TaskSelect extends BaseFragment {

    private FragmentTaskSelectBinding binding;
    private Task task;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        task = (Task) getParentFragment();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_select, container, false);
        binding.setPresenter(Presenter.getInstance());
        initView();
        initlisten();
        task.hideBack();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        UserBean userBean = UserOption.getInstance().querryUser();
        if (userBean != null) {
            binding.setUserType(userBean.getUserType());
        }
    }

    @Override
    public void initlisten() {


        binding.skgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionManagement collectionManagement = new CollectionManagement();
                Bundle bundle = new Bundle();
                bundle.putInt("missionType", 1);
                collectionManagement.setArguments(bundle);
                String title = getString(R.string.skgl);
                task.step2Task("skgl", collectionManagement, " > " + title);
            }
        });

        binding.jsgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WinLostRecord winLostRecord = new WinLostRecord();
                String title = getString(R.string.syjl);
                task.step2Task("syjl", winLostRecord, " > " + title);
            }
        });


        binding.wxgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putBoolean("isRepair", true);
                CollectionManagement collectionManagement = new CollectionManagement();
                bundle.putInt("missionType", 1);
                collectionManagement.setArguments(bundle);
                String title = getString(R.string.wxgl);
                collectionManagement.setArguments(bundle);
                task.step2Task("skgl", collectionManagement, " > " + title);
            }
        });


    }
}
