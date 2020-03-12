package com.example.rainbow.base;


import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {


    public abstract void initData();

    public abstract void initView();

    public abstract void initlisten();

    public void onKeyDown() {
    }




}
