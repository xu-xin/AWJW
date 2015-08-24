package com.xx.awjw.awjw.rent.fragement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.bean.MarkerInfoBean;
import com.xx.awjw.awjw.rent.activity.RentChooseActivity;
import com.xx.awjw.awjw.rent.activity.RentMapListActivity;
import com.xx.awjw.awjw.view.TitleView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static com.xx.awjw.awjw.bean.ZoomScaleEnum.ZoomScalelow;

/**
 * Created by Administrator on 2015/5/13.
 */
public class RentFragment extends Fragment implements View.OnClickListener{
    private TitleView mTitleView;
//    private Activity mactivity;
    private RentMapFragment rentmapfragment;
    private RentListFragment rentListFragment;
    private Fragment mContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rentfragment, null);

        initView(view);

        return view;
    }

    private void initView(View view) {


        mTitleView = (TitleView) view.findViewById(R.id.titleView);
        mTitleView.setNav_status(true);
        mTitleView.setLeft_imgbtnClickListener(this);
        mTitleView.setLeft_imgbtn1ClickListener(this);
        mTitleView.setTitle("租房");
        mTitleView.setRightImageButton(R.drawable.nav_search_selector, this);
        setFragment(getRentmapfragment());
        mContent = getRentmapfragment();

    }



    private RentMapFragment getRentmapfragment(){
        if (rentmapfragment == null){
            rentmapfragment = new RentMapFragment();
        }
        return rentmapfragment;
    }

    private RentListFragment getRentListFragment(){
        if (rentListFragment == null){
            rentListFragment = new RentListFragment();
        }
        return rentListFragment;
    }





    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.rentContent, fragment);
        transaction.commit();
    }




//    @Override
//    public void onPause() {
//        mMapView.setVisibility(View.INVISIBLE);
//        mMapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        mMapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroy() {
//        // 退出时销毁定位
//        mLocClient.stop();
//        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
//        mactivity.unregisterReceiver(mReceiver);
//        super.onDestroy();
//    }

    public void dismissMap(){
        getRentmapfragment().dismissMap();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_imgbtn:
                mTitleView.setNav_status(!mTitleView.isNav_status());
                changeFragment();
                break;
            case R.id.left_imgbtn1:
                mTitleView.setNav_status(!mTitleView.isNav_status());
                changeFragment();
                break;
            case R.id.right_imgbtn:
//                LatLng target = mBaiduMap.getMapStatus().target;//获取屏幕中心点
////                LatLng point = new LatLng(31.68306, 119.960159);
//                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
//                        .newLatLng(target);
//                mBaiduMap.animateMapStatus(mapStatusUpdate);
//                Toast.makeText(mactivity,"搜索",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void changeFragment() {
        if (mTitleView.isNav_status()){
            switchContent(mContent,getRentmapfragment());
        }else{
            switchContent(mContent,getRentListFragment());
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.rentContent, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
