package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.databinding.FragmentTaskBinding;
import com.example.rainbow.ui.fragment.task.TaskSelect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class Task extends BaseFragment {

    private FragmentTaskBinding binding;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
            binding.setTask(this);
            fm = getChildFragmentManager();
            Presenter.getInstance().setTaskFm(fm);
            initView();
        }
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        TaskSelect taskSelect = new TaskSelect();
        String title = getString(R.string.gzrw);
        step2Task("taskSelect", taskSelect, title);
    }

    public void step2Task(String name, BaseFragment fragment, String title) {
        fm.beginTransaction().replace(R.id.taskContainer, fragment).addToBackStack(name).commit();
        binding.taskToolbar.title.append(title);
        if (!title.contains(">")) {
            binding.taskToolbar.back.setVisibility(View.GONE);
        }else {
            binding.taskToolbar.back.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initlisten() {

    }

    public void hideBack(){
        binding.taskToolbar.back.setVisibility(View.GONE);
    }


    public void back() {
        BaseFragment fragment = (BaseFragment) fm.findFragmentById(R.id.taskContainer);
        if (!(fragment instanceof TaskSelect)) {
            String title = binding.taskToolbar.title.getText().toString();
            String[] titles = title.split(" > ");
            title = title.replaceAll(" > " + titles[titles.length - 1], "");
            binding.taskToolbar.title.setText(title);
            fm.popBackStack();
        }

    }
}
