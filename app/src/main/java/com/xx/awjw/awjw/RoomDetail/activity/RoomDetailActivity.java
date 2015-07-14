package com.xx.awjw.awjw.RoomDetail.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.common.Util;
import com.xx.awjw.awjw.view.TitleView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Administrator on 2015/5/15.
 */
public class RoomDetailActivity extends Activity {
    private TitleView titleView;
    private TextView sum,num;
    private HashSet<ViewGroup> unRecycledViews = new HashSet<ViewGroup>();
    private ViewPager viewpager;
    private ImagePagerAdapter adapter;
    private ImageLoadingListener animateFirstListener = new Util.AnimateFirstDisplayListener();
    private ArrayList<String> paths = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomdetail);
        initData();
        initView();
    }

    private void initData() {
        paths.add("http://b.hiphotos.baidu.com/image/pic/item/a8773912b31bb051e99173cf327adab44bede0a4.jpg");
        paths.add("http://b.hiphotos.baidu.com/image/pic/item/7aec54e736d12f2e30506e874bc2d56284356884.jpg");
        paths.add("http://a.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3d305524d7bea1cd11738b29a5.jpg");
        paths.add("http://g.hiphotos.baidu.com/image/pic/item/9a504fc2d56285356b4012c694ef76c6a6ef63b1.jpg");
    }

    private void initView() {
        titleView = (TitleView)findViewById(R.id.titleView);
        titleView.setTitle("梅山大楼");
        titleView.setBackGround(R.color.transparent);
        titleView.setLeftImageButton( R.drawable.roomdetail_back_gray_selector, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomDetailActivity.this.finish();
            }
        });

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ImagePagerAdapter();
        sum = (TextView) findViewById(R.id.sum);
        num = (TextView) findViewById(R.id.num);
        viewpager.setAdapter(adapter);
        sum.setText(paths.size()+"");
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
        viewpager = (ViewPager) findViewById(R.id.viewpager);
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
            View contentView = inflater.inflate(R.layout.imagefragment, view,
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

        final ImageView imageView = (ImageView) contentView
                .findViewById(R.id.iv_photo);
//		final PhotoView imageView = (PhotoView) contentView
//				.findViewById(R.id.iv_photo);
        imageView.setVisibility(View.VISIBLE);
        Util.imageLoader.displayImage(paths.get(position),
//				Util.imageLoader.displayImage("file://" + paths.get(position),
//				Util.imageLoader.displayImage("http://p3.so.qhimg.com/bdr/_240_/t01d96a633fde32ebed.jpg",
                imageView, Util.image_display_options, animateFirstListener);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomDetailActivity.this,PictureShowActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
//		imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//					@Override
//					public void onPhotoTap(View view, float x, float y) {
//						setresult();
//						return;
//
//					}
//
//				});
    }
}
