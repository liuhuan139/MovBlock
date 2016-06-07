package com.liuhuan.cydier.movblock.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.liuhuan.cydier.movblock.Iterface.IStartAnimation;
import com.liuhuan.cydier.movblock.Layout.BallContainerLayout;
import com.liuhuan.cydier.movblock.R;

public class MainActivity extends ActionBarActivity implements IStartAnimation {
    private ScaleAnimation scaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        BallContainerLayout containerLayout = new BallContainerLayout(this);
        ViewGroup vg = (ViewGroup) this.findViewById(R.id.container);
        vg.addView(containerLayout);

        createAnimation();
        containerLayout.setStartAnimationListener(this);
    }

    private void createAnimation() {
        scaAnimation = new ScaleAnimation(0f, 2f, 0f, 2f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        scaAnimation.setDuration(2000);
		scaAnimation.setFillAfter(true);
		scaAnimation.setRepeatCount(1);
    }

    @Override
    public void configAnimation(View view) {
        view.clearAnimation();
        view.setAnimation(scaAnimation);
        scaAnimation.startNow();
    }
}
