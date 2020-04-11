package com.example.rainbow.util;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

public class InputFilterMax implements InputFilter {
    private int min, max;
    private Context context;

    public InputFilterMax(Context context, int min, int max) {
        this.context = context;
        this.min = min;
        this.max = max;
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            LogUtil.log(max+"=============max============="+input);
            if (isInRange(min, max, input)) {
                return null;
            }
        } catch (Exception nfe) {
        }
        Toast.makeText(context, "最大洗分不能超过" + max, Toast.LENGTH_SHORT).show();
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b>c;
    }
}
