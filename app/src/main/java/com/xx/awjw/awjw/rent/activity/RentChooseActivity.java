package com.xx.awjw.awjw.rent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xx.awjw.awjw.R;


/**
 * Created by Administrator on 2015/7/15 0015.
 */
public class RentChooseActivity extends Activity implements Animation.AnimationListener{
    private LinearLayout ll;
    private LinearLayout ll_top;
    private RelativeLayout rl;
    private LinearLayout tx_sure;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rentchoose);
        initView();
    }

    private void initView() {
        rl = (RelativeLayout) findViewById(R.id.rl);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        tx_sure = (LinearLayout) findViewById(R.id.tx_sure);
        final Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.activity_up);
        animation.reset();
        //  animation.setFillAfter(true);
        animation.setAnimationListener(this);
        ll.setAnimation(animation);
//        animation.start();
        final Animation animation1   =    AnimationUtils.loadAnimation(this, R.anim.activity_up);
        animation.reset();
//          animation.setFillAfter(true);
        tx_sure.setAnimation(animation1);
//        animation1.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.start();
                animation1.start();
            }
        },500);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        ll.setVisibility(View.VISIBLE);
        tx_sure.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        ll_top.setBackgroundResource(R.drawable.select_bg);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
