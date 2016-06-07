package com.liuhuan.cydier.movblock.Layout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liuhuan.cydier.movblock.Iterface.IStartAnimation;
import com.liuhuan.cydier.movblock.R;

/**
 * Created by cydier on 2016/6/7.
 */
public class BallContainerLayout extends BaseLayout {
    private ImageView imageView;
    private View centerView;
    private Rect centerImageRec;
    private static final int IMAGEVIEW_WIDTH = 60;
    private static final int IMAGEVIEW_HEIGHT = 60;
    private IStartAnimation iStartAnimation;


    public BallContainerLayout(Context context) {
        super(context);
    }

    public BallContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initView() {
        initImageView();
    }

    private void initImageView() {
        imageView = new ImageView(mContext);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        centerView = inflate(mContext, R.layout.redrec, null);
        this.addView(imageView);
        this.addView(centerView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        imageView.layout(l, t, l + IMAGEVIEW_WIDTH, t + IMAGEVIEW_HEIGHT);
        int x = disPlayPoint.x;
        int y = disPlayPoint.y;

        centerImageRec = new Rect(x / 2 - IMAGEVIEW_WIDTH / 2, y / 2
                - IMAGEVIEW_HEIGHT / 2, x / 2 + IMAGEVIEW_WIDTH / 2, y / 2
                + IMAGEVIEW_HEIGHT / 2);
        centerView.layout(centerImageRec.left,centerImageRec.top,centerImageRec.right,centerImageRec.bottom);
    }

    protected void onAttachedToWindow() {
        imageView.setOnTouchListener(ImageTouchListener);
    }

    OnTouchListener ImageTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            int action = event.getAction();
            float downX = 0f;
            float downY = 0f;
            float dx = 0f;
            float dy = 0f;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float MoveX = event.getX();
                    float MoveY = event.getY();

                    dx = MoveX - downX;
                    dy = MoveY - downY;

                    downX = MoveX;
                    downY = MoveY;

                    dealTouchEvent(dx, dy);
                    if(checkInCenter()){
                        centerView.setVisibility(View.GONE);
                    }else{
                        centerView.setVisibility(View.VISIBLE);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    if(checkInCenter()){
                        view.setOnTouchListener(null);
                        iStartAnimation.configAnimation(view);
                    }
                    break;
            }
            return true;
        }

        private boolean checkInCenter() {
            int centerY = imageView.getBottom() - IMAGEVIEW_HEIGHT/2;
            int centerX = imageView.getRight() - IMAGEVIEW_WIDTH/2;

            return centerImageRec.contains(centerX, centerY);
        }

        protected void dealTouchEvent(float dx, float dy) {
            imageView.layout(Math.round(imageView.getLeft() + dx),
                    Math.round(imageView.getTop() + dy),
                    Math.round(imageView.getRight() + dx),
                    Math.round(imageView.getBottom() + dy));
            invalidate();
        }
    };

    public void setStartAnimationListener(IStartAnimation iStartAnimation){
        this.iStartAnimation = iStartAnimation;
    }
}
