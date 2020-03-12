package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.databinding.FragmentTaskSelectBinding;
import com.example.rainbow.ui.fragment.MainFragment;
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
        initlisten();
        task.hideBack();
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


        binding.skgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionManagement collectionManagement = new CollectionManagement();
                String title = getString(R.string.skgl);
                task.step2Task("skgl", collectionManagement, " > "+title);
            }
        });

        binding.jsgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WinLostRecord winLostRecord = new WinLostRecord();
                String title = getString(R.string.syjl);
                task.step2Task("syjl",winLostRecord," < "+title);

            }
        });

    }
}
