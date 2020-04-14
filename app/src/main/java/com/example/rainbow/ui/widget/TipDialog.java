package com.example.rainbow.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.rainbow.R;
import com.example.rainbow.base.RainBowApplication;
import com.example.rainbow.databinding.LayoutTipBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.ClickSureListener;

import androidx.databinding.DataBindingUtil;

public class TipDialog extends Dialog {

    private LayoutTipBinding binding;

    public TipDialog(Context context, String title, ClickSureListener clickSureListener) {
        super(context,R.style.app_dialog);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_tip, null, false);
        binding.setTitle(title);
        binding.cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
               clickSureListener.clickSure();

            }
        });
        setCancelable(false);
        setContentView(binding.getRoot());
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        int windowWidth = RainBowApplication.getApplication().getWindowWidth();
        layoutParams.width = (windowWidth * 400) / 1092;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.6f;
        getWindow().setAttributes(layoutParams);
    }


}
