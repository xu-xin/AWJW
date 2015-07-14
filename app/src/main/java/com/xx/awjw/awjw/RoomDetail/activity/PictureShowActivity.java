package com.xx.awjw.awjw.RoomDetail.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.common.Util;

import java.util.ArrayList;
import java.util.HashSet;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureShowActivity extends Activity {
	
	private TextView sum,num;
	private HashSet<ViewGroup> unRecycledViews = new HashSet<ViewGroup>();
	private ViewPager viewpager;
	private ImagePagerAdapter adapter;
	private ImageLoadingListener animateFirstListener = new Util.AnimateFirstDisplayListener();
	private ArrayList<String> paths = new ArrayList<String>();
	private int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pictureshow);
		initData();
		initView();
	}

	
	private void initData() {
		position = getIntent().getIntExtra("position", 0);
		paths.add("http://b.hiphotos.baidu.com/image/pic/item/a8773912b31bb051e99173cf327adab44bede0a4.jpg");
		paths.add("http://b.hiphotos.baidu.com/image/pic/item/7aec54e736d12f2e30506e874bc2d56284356884.jpg");
		paths.add("http://a.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3d305524d7bea1cd11738b29a5.jpg");
		paths.add("http://g.hiphotos.baidu.com/image/pic/item/9a504fc2d56285356b4012c694ef76c6a6ef63b1.jpg");
	}


	private void initView() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		adapter = new ImagePagerAdapter();
		sum = (TextView) findViewById(R.id.sum);
		num = (TextView) findViewById(R.id.num);
		viewpager.setAdapter(adapter);
		sum.setText(paths.size()+"");
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				num.setText(arg0+1+"");
				
			}
			
		});
		viewpager.setCurrentItem(position);
	}


	private class ImagePagerAdapter extends PagerAdapter {

		private LayoutInflater inflater;

		public ImagePagerAdapter() {
			inflater = getLayoutInflater();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (object instanceof ViewGroup) {
				((ViewPager) container).removeView((View) object);
				unRecycledViews.remove(object);
				ViewGroup viewGroup = (ViewGroup) object;

			}
		}

		@Override
		public int getCount() {
			return paths.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View contentView = inflater.inflate(R.layout.pictureshow_item, view,
					false);

			handlePage(position, contentView, true);

			((ViewPager) view).addView(contentView, 0);
			unRecycledViews.add((ViewGroup) contentView);
			return contentView;
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			super.setPrimaryItem(container, position, object);
			View contentView = (View) object;
			if (contentView == null) {
				return;
			}

			ImageView imageView = (ImageView) contentView
					.findViewById(R.id.iv_photo);

			if (imageView.getDrawable() != null) {
				return;
			}

			handlePage(position, contentView, false);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

	}
	
	private void handlePage(final int position, View contentView,
			boolean fromInstantiateItem) {

		final PhotoView imageView = (PhotoView) contentView
				.findViewById(R.id.iv_photo);
		imageView.setVisibility(View.VISIBLE);
		Util.imageLoader.displayImage(paths.get(position),
//				Util.imageLoader.displayImage("file://" + paths.get(position),
//				Util.imageLoader.displayImage("http://p3.so.qhimg.com/bdr/_240_/t01d96a633fde32ebed.jpg",
				imageView, Util.image_display_options, animateFirstListener);
		imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
					@Override
					public void onPhotoTap(View view, float x, float y) {
						setresult();
						return;

					}

				});
	}
	
	private void setresult() {
		Intent intent = new Intent();
		intent.putStringArrayListExtra("pictures", paths);
		setResult(101, intent);
		PictureShowActivity.this.finish();
	}

	@Override
	public void onBackPressed() {
		setresult();
		super.onBackPressed();
	}

}
