package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.databinding.FragmentPersonDetailBinding;
import com.example.rainbow.ui.fragment.PersonalData;
import com.example.rainbow.ui.fragment.task.TaskSelect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class PersonDetail extends BaseFragment {


    private FragmentPersonDetailBinding binding;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_detail, container, false);
        binding.setPersonDetail(this);
        fm = getChildFragmentManager();
        initView();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        PersonalData personalData = new PersonalData();
        String title = getString(R.string.grxx);
        step2PersonDetail("personData", personalData, title);
    }

    public void step2PersonDetail(String name, BaseFragment fragment, String title) {
        fm.beginTransaction().replace(R.id.personDetailContainer, fragment).addToBackStack(name).commit();
        binding.personDetailToolBar.title.append(title);
        if (!title.contains(">")) {
            binding.personDetailToolBar.back.setVisibility(View.GONE);
        }else {
            binding.personDetailToolBar.back.setVisibility(View.VISIBLE);
        }
    }

    public void hideBack(){
        binding.personDetailToolBar.back.setVisibility(View.GONE);
    }


    @Override
    public void initlisten() {

    }

    public void back() {
        BaseFragment fragment = (BaseFragment) fm.findFragmentById(R.id.personDetailContainer);
        if (!(fragment instanceof PersonalData)) {
            String title = binding.personDetailToolBar.title.getText().toString();
            String[] titles = title.split(" > ");
            title = title.replaceAll(" > " + titles[titles.length - 1], "");
            binding.personDetailToolBar.title.setText(title);
            fm.popBackStack();
        }
    }


}
