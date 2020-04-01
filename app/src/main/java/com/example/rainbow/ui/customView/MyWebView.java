package com.example.rainbow.ui.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.example.rainbow.util.RudenessScreenHelper;


public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setOverScrollMode(int mode){
        super.setOverScrollMode(mode);
        RudenessScreenHelper.resetDensity(getContext(), 1920);
    }
}
