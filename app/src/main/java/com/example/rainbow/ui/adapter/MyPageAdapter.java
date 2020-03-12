package com.example.rainbow.ui.adapter;

import com.example.rainbow.base.BaseFragment;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private ArrayList<BaseFragment> fragments;


    public MyPageAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }



    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }


    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
