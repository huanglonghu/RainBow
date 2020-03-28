package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentMainBinding;
import com.example.rainbow.ui.adapter.MyViewPagerAdapter;
import com.example.rainbow.ui.main.ClockIn;
import com.example.rainbow.ui.main.Notice;
import com.example.rainbow.ui.main.PersonDetail;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.ui.main.Wxzn;
import com.example.rainbow.ui.main.WxznList;

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
            UserBean userBean = UserOption.getInstance().querryUser();
            if (userBean != null) {
                binding.setUserType(userBean.getUserType());
            }
            initView();
            initlisten();
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
        PersonDetail personDetail = new PersonDetail();
        Notice notice = new Notice();
        fragments.add(task);
        fragments.add(personDetail);
        fragments.add(notice);
        if (binding.getUserType() == 3) {
            Wxzn wxzn = new Wxzn();
            fragments.add(wxzn);
        }else {
            ClockIn clockIn = new ClockIn();
            fragments.add(clockIn);
        }


        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), fragments);
        binding.vpMain.setAdapter(pagerAdapter);
        String[] languages = getResources().getStringArray(R.array.language);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item2, languages);
        adapter.setDropDownViewResource(R.layout.spinner_item3);
        binding.spLanguage.setAdapter(adapter);
    }

    public void toggleVpMain(int position) {
        binding.vpMain.setCurrentItem(position, false);
        binding.setSelectPosition(position);
    }

    @Override
    public void initlisten() {
        binding.config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = binding.leftll.getVisibility();
                if (visibility == View.VISIBLE) {
                    binding.leftll.setVisibility(View.GONE);
                } else {
                    binding.leftll.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void exit() {
        System.exit(0);
    }

}
