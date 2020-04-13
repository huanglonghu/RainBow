package com.example.rainbow.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.rainbow.R;
import com.example.rainbow.base.RainBowApplication;
import com.example.rainbow.bean.PartResponse;
import com.example.rainbow.ui.adapter.PartMenuAdapter;

import java.util.List;


public class PartMenuWindow extends PopupWindow {


    public PartMenuWindow(Context context, List<PartResponse.DataBean.ItemsBean> items) {
        int windowWidth = RainBowApplication.getApplication().getWindowWidth();
        int s = (windowWidth * 200) / 1920;
        setWidth(s);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_partmenu, null, false);
        setContentView(view);
        ListView lvPart = view.findViewById(R.id.lv_part);
        PartMenuAdapter partMenuAdapter = new PartMenuAdapter(context, items, R.layout.lv_item_part);
        lvPart.setAdapter(partMenuAdapter);
        setOutsideTouchable(true);
    }


}
