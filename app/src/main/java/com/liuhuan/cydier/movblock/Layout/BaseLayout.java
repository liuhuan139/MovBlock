package com.liuhuan.cydier.movblock.Layout;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

/**
 * Created by cydier on 2016/6/7.
 */
public abstract class BaseLayout extends ViewGroup {
    protected Context mContext;
    protected Point disPlayPoint;
    public BaseLayout(Context context) {
        super(context);
        init(context);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView();
        getDisplayPoint();
    }

    public abstract void initView();

    private void getDisplayPoint() {
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        disPlayPoint = new Point(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
