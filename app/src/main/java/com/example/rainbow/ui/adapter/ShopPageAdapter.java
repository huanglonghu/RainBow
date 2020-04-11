package com.example.rainbow.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class ShopPageAdapter extends PagerAdapter {
    private List<ListView> views;
    private String[] titles;

    public ShopPageAdapter(List<ListView> views, String[] titles) {
        this.views = views;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
