package com.example.rainbow.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.rainbow.MainActivity;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentMainBinding;
import com.example.rainbow.language.LanguagesManager;
import com.example.rainbow.ui.adapter.MyViewPagerAdapter;
import com.example.rainbow.ui.main.ClockIn;
import com.example.rainbow.ui.main.Notice;
import com.example.rainbow.ui.main.PersonDetail;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.ui.main.Wxzn;
import com.example.rainbow.ui.main.WxznList;
import com.example.rainbow.util.LogUtil;

import java.util.ArrayList;
import java.util.Locale;

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
        } else {
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

    private boolean isInit;

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

        Locale locale = LanguagesManager.getAppLanguage(getContext());
        Locale spanish = new Locale("es", "ES");
        if (LanguagesManager.equalsCountry(locale, Locale.CHINA)) {
            binding.spLanguage.setSelection(0);
        } else if (LanguagesManager.equalsCountry(locale, Locale.ENGLISH)) {
            binding.spLanguage.setSelection(1);
        } else if (LanguagesManager.equalsCountry(locale,spanish)) {
            binding.spLanguage.setSelection(2);
        }
        binding.spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean restart;
                if (isInit) {
                    if (position == 0) {
                        Locale locale = Locale.CHINA;
                        restart = LanguagesManager.setAppLanguage(getContext(), locale);
                    } else {
                        Locale spanish = new Locale("es", "ES");
                        restart = LanguagesManager.setAppLanguage(getContext(), spanish);
                    }
                    if (restart) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                    }

                }
                isInit = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void exit() {
        System.exit(0);
    }

}
