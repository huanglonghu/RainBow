package com.example.rainbow.util;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

public class InputFilterMax implements InputFilter {
    private int max;
    private Context context;
    private String toastStr;

    public InputFilterMax(Context context, String toastStr, int max) {
        this.context = context;
        this.max = max;
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
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
