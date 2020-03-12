package com.example.rainbow.ui.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;


import com.example.rainbow.R;
import com.example.rainbow.util.RudenessScreenHelper;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;


public class ImageTextView extends AppCompatTextView {

    private int direcation;

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    private Drawable drawable;
    private int scaleWidth; //dp值
    private int scaleHeight;
    private Context context;

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView);
        drawable = typedArray.getDrawable(R.styleable.ImageTextView_drawable);

        scaleWidth = typedArray.getDimensionPixelOffset(R.styleable
                .ImageTextView_drawableWidth, (int) RudenessScreenHelper.pt2px(context,20));
        scaleHeight = typedArray.getDimensionPixelOffset(R.styleable
                .ImageTextView_drawableHeight, (int) RudenessScreenHelper.pt2px(context,20));
        direcation = typedArray.getInt(R.styleable.ImageTextView_direcation, 0);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (drawable != null) {
            drawable.setBounds(0, 0, (int) RudenessScreenHelper.pt2px(context,scaleWidth),(int) RudenessScreenHelper.pt2px(context,scaleHeight));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (direcation) {
            case 0:
                this.setCompoundDrawables(drawable, null, null, null);
                break;
            case 1:
                this.setCompoundDrawables(null, drawable, null, null);
                break;
            case 2:
                this.setCompoundDrawables(null, null, drawable, null);
                break;
            case 3:
                this.setCompoundDrawables(null, null, null, drawable);
                break;
        }

    }






    @BindingAdapter(value = {"drawable"})
    public static void setValue(ImageTextView imageTextView, Drawable drawableLeft) {
        imageTextView.setDrawable(drawableLeft);
    }


}