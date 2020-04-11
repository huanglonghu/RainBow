package com.example.rainbow.util;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

public class InputFilterMax2 implements InputFilter {
    private double max;
    private Context context;
    private String toastStr;

    public InputFilterMax2(Context context, String toastStr, double max) {
        this.context = context;
        this.toastStr = toastStr;
        this.max = max;
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            LogUtil.log(max + "=============max=============" + input);
            if (max >= input) {

                return null;
            }
        } catch (Exception nfe) {
        }
        Toast.makeText(context, toastStr + max, Toast.LENGTH_SHORT).show();
        return "";
    }


}
