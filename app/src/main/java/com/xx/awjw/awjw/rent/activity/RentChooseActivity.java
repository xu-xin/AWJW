package com.xx.awjw.awjw.rent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xx.awjw.awjw.R;


/**
 * Created by Administrator on 2015/7/15 0015.
 */
public class RentChooseActivity extends Activity implements Animation.AnimationListener, View.OnClickListener {
    private RelativeLayout ll;
    private LinearLayout ll_top;
    private RelativeLayout rl;
    private LinearLayout tx_sure;
    private Button cancle;
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
        ll = (RelativeLayout) findViewById(R.id.ll);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        tx_sure = (LinearLayout) findViewById(R.id.tx_sure);
        cancle = (Button) findViewById(R.id.cancle);
        cancle.setOnClickListener(this);
        ll_top.setOnClickListener(this);
        final Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.activity_up);
        animation.reset();
        //  animation.setFillAfter(true);
        animation.setAnimationListener(this);
        animation.setDuration(500);
        ll.setAnimation(animation);
//        animation.start();
//          animation.setFillAfter(true);
//        animation1.setDuration(1000);
//        animation1.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        },100);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        ll.setVisibility(View.VISIBLE);
        tx_sure.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        ll_top.setBackgroundResource(R.drawable.black_transparent_bg);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onBackPressed() {
        close();
    }

    private  void close(){
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.activity_down);
        animation.reset();
        //  animation.setFillAfter(true);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ll_top.setBackgroundColor(getResources().getColor(R.color.transparent));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RentChooseActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ll.clearAnimation();
        ll.setAnimation(animation);
        animation.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.cancle:
                close();
                break;
            case  R.id.ll_top:
                close();
                break;
        }
    }
}
