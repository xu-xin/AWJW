package com.xx.awjw.awjw.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xx.awjw.awjw.R;

/**
 * Created by Administrator on 2015/5/7.
 */
public class TabView extends LinearLayout {

    private Context mcontext;
    private TextView mTextView;
    private ImageView mImageView;
    private LayoutInflater mInflater;

    private Drawable imageNormal = getResources().getDrawable(R.drawable.ic_launcher);
    private Drawable imageChecked = getResources().getDrawable(R.drawable.ic_launcher);
    private int colorNormal = R.color.black;
    //    private int colorNormal = R.color.black;
    private int colorChecked = R.color.black;
    //    private int colorChecked = R.color.orange;
    private Boolean checked = false;

    private Activity activity;


    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
        setStatusChecked(checked);



    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;
        this.mInflater = LayoutInflater.from(this.mcontext);
        View view = this.mInflater.inflate(R.layout.tabview, null);
        this.mImageView = (ImageView) view.findViewById(R.id.tabview_iv);
        this.mTextView = (TextView) view.findViewById(R.id.tabview_tv);
        this.mImageView.setImageDrawable(this.imageNormal);
        this.mTextView.setTextColor(this.colorNormal);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        String text = a.getString(R.styleable.TabView_text);
        if (text != null) {
            this.mTextView.setText(text);
        }
        Drawable imageN = a.getDrawable(R.styleable.TabView_imageNormal);
        if (imageN != null) {
            this.mImageView.setImageDrawable(imageN);
            this.imageNormal = imageN;
        }
        Drawable imageC = a.getDrawable(R.styleable.TabView_imageChecked);
        if (imageC != null) {
            this.imageChecked = imageC;
        }

        int colorN = a.getColor(R.styleable.TabView_textcolorNormal,R.color.black);
        this.mTextView.setTextColor(colorN);
        this.colorNormal = colorN;

        int colorC = a.getColor(R.styleable.TabView_textcolorChecked,R.color.black);
        this.colorChecked = colorC;
        a.recycle();
        addView(view);
    }

    public TabView(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setStatusChecked(true);
                Toast.makeText(mcontext,"点击了",Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getRawX()>this.getRight()){
                    if (mImageView.getDrawable() == imageChecked){
                        setStatusChecked(false);
                        Toast.makeText(mcontext,"超出了控件范围",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                setStatusChecked(false);
                Toast.makeText(mcontext,"手指抬起了",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setStatusChecked(Boolean statusChecked) {
        if (statusChecked) {
            this.mImageView.setImageDrawable(this.imageChecked);
            this.mTextView.setTextColor(this.colorChecked);
        }else{
            this.mImageView.setImageDrawable(this.imageNormal);
            this.mTextView.setTextColor(this.colorNormal);
        }
    }
}
