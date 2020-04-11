package com.example.rainbow.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.rainbow.R;
import com.example.rainbow.base.RainBowApplication;


public class BigImg extends PopupWindow {

    private Bitmap bitmap;

    public BigImg(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
        setAnimationStyle(R.style.popupStyle2);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        int windownWidth = RainBowApplication.getApplication().getWindowWidth();
        int i = (windownWidth / width) * height;
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        setContentView(imageView);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setFocusable(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
