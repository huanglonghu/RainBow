package com.example.rainbow.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.rainbow.R;
import com.example.rainbow.base.RainBowApplication;
import com.example.rainbow.databinding.LayoutPhotographBinding;
import com.example.rainbow.ui.adapter.PhotoGraphWindowAdpter;
import com.example.rainbow.util.RudenessScreenHelper;

import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoGraphWindow extends PopupWindow {

    private LayoutPhotographBinding binding;
    private Context context;

    public PhotoGraphWindow(Context context, String[] pathArray) {
        this.context = context;
        setFocusable(false);
        setOutsideTouchable(false);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        int windowWidth = RainBowApplication.getApplication().getWindowWidth();
        int h = (windowWidth * 220) / 1920;
        setHeight(h);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_photograph, null, false);
        setContentView(binding.getRoot());
        setOutsideTouchable(true);
        initRvItem(pathArray, binding.arRecycle);
    }



    private void initRvItem(String[] pathArray, RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider3));
        recyclerView.addItemDecoration(divider);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        PhotoGraphWindowAdpter photoGraphWindowAdpter = new PhotoGraphWindowAdpter(context, pathArray);
        recyclerView.setAdapter(photoGraphWindowAdpter);
    }

}

