package com.xx.awjw.awjw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xx.awjw.awjw.R;


/**
 * @author xuxin
 */
public class TitleView extends FrameLayout {
	private TextView mTitle;
	private Button mLeftBtn;
	private Button mRightBtn;
	private ImageButton left_imgbtn;
	private ImageButton left_imgbtn1;
	private ImageButton right_imgbtn;
	private RelativeLayout relativelayout;

	private Boolean nav_status = false;

	public TitleView(Context context) {
		this(context, null);
	}

	public TitleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_view, this, true);
		mTitle = (TextView) findViewById(R.id.title_text);
		mLeftBtn = (Button) findViewById(R.id.left_btn);
		mRightBtn = (Button) findViewById(R.id.right_btn);
		left_imgbtn = (ImageButton) findViewById(R.id.left_imgbtn);
		left_imgbtn1 = (ImageButton) findViewById(R.id.left_imgbtn1);
		right_imgbtn = (ImageButton) findViewById(R.id.right_imgbtn);
		relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
		mLeftBtn.setVisibility(View.GONE);
		mRightBtn.setVisibility(View.GONE);
		left_imgbtn.setVisibility(View.GONE);
		left_imgbtn1.setVisibility(View.GONE);
		right_imgbtn.setVisibility(View.GONE);
	}

	public void setTitle(String textContext) {
		mTitle.setText(textContext);
	}
	
	public void setTitle(String text,OnClickListener listener){
		mTitle.setText(text);
		if(listener!=null){
			mTitle.setOnClickListener(listener);
		}
	}

	public String getTitle() {
		return mTitle.getText().toString();
	}

	public void setLeftButton(String text, int bgId, OnClickListener listener) {
		mLeftBtn.setVisibility(View.VISIBLE);
		mLeftBtn.setText(text);
		mLeftBtn.setBackgroundResource(bgId);
		if (listener != null)
			mLeftBtn.setOnClickListener(listener);
	}
	
	public void setLeftButton(int text_id, int bgId, OnClickListener listener) {
		mLeftBtn.setVisibility(View.VISIBLE);
		mLeftBtn.setText(text_id);
		mLeftBtn.setBackgroundResource(bgId);
		if (listener != null)
			mLeftBtn.setOnClickListener(listener);
	}

	public void hideLeftButton() {
		mLeftBtn.setVisibility(View.INVISIBLE);
	}

	public void setLeftImageButton(int bgId, OnClickListener listener) {
		left_imgbtn.setVisibility(View.VISIBLE);
		left_imgbtn.setImageResource(bgId);
		if (listener != null)
			left_imgbtn.setOnClickListener(listener);
	}
	public void setRightImageButton(int bgId, OnClickListener listener) {
		right_imgbtn.setVisibility(View.VISIBLE);
		right_imgbtn.setImageResource(bgId);
		if (listener != null)
			right_imgbtn.setOnClickListener(listener);
	}
	public void setRightImageButton(int bgId,int imgId, OnClickListener listener) {
		right_imgbtn.setVisibility(View.VISIBLE);
		right_imgbtn.setImageResource(imgId);
		right_imgbtn.setBackgroundResource(bgId);
		if (listener != null)
			right_imgbtn.setOnClickListener(listener);
	}
	
	public void setLeftImageButton(int bgId,String content,OnClickListener listener){
		left_imgbtn.setVisibility(View.VISIBLE);
		left_imgbtn.setImageResource(bgId);
		left_imgbtn.setContentDescription(content);
		if (listener != null)
			left_imgbtn.setOnClickListener(listener);
	}

	public void setRightButton(String text, int bgId, OnClickListener listener) {
		mRightBtn.setVisibility(View.VISIBLE);
		mRightBtn.setText(text);
		mRightBtn.setBackgroundResource(bgId);
		if (listener != null)
			mRightBtn.setOnClickListener(listener);
	}
	
	public void setRightButton(int text_id, int bgId, OnClickListener listener) {
		mRightBtn.setVisibility(View.VISIBLE);
		mRightBtn.setText(text_id);
		mRightBtn.setBackgroundResource(bgId);
		if (listener != null)
			mRightBtn.setOnClickListener(listener);
	}

	public void setLeftButtonTextSize(int size) {
		mLeftBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
		mLeftBtn.setGravity(Gravity.CENTER);
	}

	public void setRightButtonTextSize(int size) {
		mRightBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
		mRightBtn.setGravity(Gravity.CENTER);
	}

	public void setLeftButtonTextColor(String color) {
		mLeftBtn.setTextColor(android.graphics.Color.parseColor(color));
		mLeftBtn.setGravity(Gravity.CENTER);
	}

	public void setRightButtonTextColor(String color) {
		mRightBtn.setTextColor(android.graphics.Color.parseColor(color));
		mRightBtn.setGravity(Gravity.CENTER);
	}
	public void setRgihtUbttonVisable(boolean result){
		if(result){
			mRightBtn.setVisibility(View.VISIBLE);
		}
		if(!result){
			mRightBtn.setVisibility(View.INVISIBLE);
		}
	}
	
	public View getRightButton(){
		return mTitle;
	}
	
	public void setBackGround(int resourceId){
		relativelayout.setBackgroundResource(resourceId);
	}

	public Boolean isNav_status() {
		return nav_status;
	}

	public void setLeft_imgbtnClickListener(OnClickListener listener){
		left_imgbtn.setOnClickListener(listener);

	}
	public void setLeft_imgbtn1ClickListener(OnClickListener listener){
		left_imgbtn1.setOnClickListener(listener);
	}

	public void setNav_status(Boolean nav_status) {
		this.nav_status = nav_status;
		left_imgbtn.setVisibility(VISIBLE);
		left_imgbtn1.setVisibility(VISIBLE);
		if (this.nav_status){
			left_imgbtn.setBackgroundResource(R.drawable.nav_map_pre);
			left_imgbtn1.setBackgroundResource(R.drawable.nav_list_nor);
		}else{
			left_imgbtn.setBackgroundResource(R.drawable.nav_map_nor);
			left_imgbtn1.setBackgroundResource(R.drawable.nav_list_pre);
		}
	}
}
