package com.xx.awjw.awjw.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.xx.awjw.awjw.R;

import java.util.ArrayList;


@SuppressLint("NewApi")
public class OptionLayout extends LinearLayout{
	
	private ToggleButton option1,option2,option3,option4;
	private CharSequence[] mTextArray;
	private ArrayList<ToggleButton> toggles = new ArrayList<ToggleButton>();

	public OptionLayout(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public OptionLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public OptionLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater mInflater = LayoutInflater.from(context);
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.OptionLayout);	
		mTextArray = a.getTextArray(R.styleable.OptionLayout_optionTextArray);
		
		a.recycle();
		View view = mInflater.inflate(R.layout.option, null);
		option1 = (ToggleButton) view.findViewById(R.id.option1);
		option2 = (ToggleButton) view.findViewById(R.id.option2);
		option3 = (ToggleButton) view.findViewById(R.id.option3);
		option4 = (ToggleButton) view.findViewById(R.id.option4);
		option1.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		option1.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		option2.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		option3.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		option4.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		toggles.add(option1);
		toggles.add(option2);
		toggles.add(option3);
		toggles.add(option4);
		for(int i = 0;i<mTextArray.length;i++){
			toggles.get(i).setVisibility(View.VISIBLE);
			toggles.get(i).setText(mTextArray[i]);
			toggles.get(i).setTextOff(mTextArray[i]);
			toggles.get(i).setTextOn(mTextArray[i]);
		}
		addView(view);
	}

	public OptionLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	private class MyOnCheckedChangeListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if(isChecked){
				buttonView.setBackgroundResource(R.drawable.option_bg_on);
				buttonView.setTextColor(getResources().getColor(R.color.orange));
	
			}else{
				buttonView.setBackgroundResource(R.drawable.option_bg_off);
				buttonView.setTextColor(getResources().getColor(R.color.black));
			}
		}
		
	}

}
