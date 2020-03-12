package com.example.rainbow.ui.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LinePathView2 extends View {

    private Paint mGesturePaint;
    private Path path;
    private Bitmap mCatcheMap;

    public LinePathView2(Context context) {
        super(context);
    }

    public LinePathView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinePathView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGesturePaint = new Paint();
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(5);
        mGesturePaint.setColor(Color.BLACK);

        path = new Path();

        mCatcheMap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
