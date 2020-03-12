package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.databinding.FragmentMainBinding;
import com.example.rainbow.ui.adapter.MyViewPagerAdapter;
import com.example.rainbow.ui.main.ClockIn;
import com.example.rainbow.ui.main.Notice;
import com.example.rainbow.ui.main.PersonDetail;
import com.example.rainbow.ui.main.Task;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class MainFragment extends BaseFragment {

    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            binding.setPresenter(Presenter.getInstance());
            Presenter.getInstance().setMainFm(getChildFragmentManager());
            binding.setMain(this);
            initView();
        }
        return binding.getRoot();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        Task task = new Task();
        ClockIn clockIn = new ClockIn();
        PersonDetail personDetail = new PersonDetail();
        Notice notice = new Notice();
        fragments.add(task);
        fragments.add(clockIn);
        fragments.add(personDetail);
        fragments.add(notice);
        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), fragments);
        binding.vpMain.setAdapter(pagerAdapter);


        String[] languages= {"简体中文","英语","西班牙语"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item2,languages);
        adapter.setDropDownViewResource(R.layout.spinner_item3);
        binding.spLanguage.setAdapter(adapter);


    }

    public void toggleVpMain(int position) {
        binding.vpMain.setCurrentItem(position, false);
        binding.setSelectPosition(position);
    }

    @Override
    public void initlisten() {

    }

    public void exit(){
        System.exit(0);
    }


}