package com.xx.awjw.awjw.rent.fragement;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private Activity mactivity;
    private LinearLayout ll;


    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private UiSettings mUiSettings;
    // 定位相关
    private LocationClient mLocClient;
    private MyLocationListenner myListener = new MyLocationListenner();
    private int zoomScale = ZoomScalelow.value();

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
//                text.setText("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s
                    .equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
//                text.setText("网络出错");
            }
        }
    }

    private SDKReceiver mReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rentfragment, null);

        initView(view);
        //添加覆盖物
        initOverlayView();

        //添加地图监听
        addStatusChange();
        return view;
    }

    private void initView(View view) {
        mactivity = this.getActivity();
        ll = (LinearLayout) view.findViewById(R.id.ll);
        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        mactivity.registerReceiver(mReceiver, iFilter);

        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mUiSettings = mBaiduMap.getUiSettings();
        //是否启用旋转手势
        mUiSettings.setRotateGesturesEnabled(false);
        //是否启用俯视手势
        mUiSettings.setOverlookingGesturesEnabled(false);
        //设置缩放级别
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                .zoomTo(12.0f);
//                .zoomBy(zoomScale);
        mBaiduMap.animateMapStatus(mapStatusUpdate);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //百度地图加载完成回调函数
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                ll.setVisibility(View.GONE);
                mMapView.setVisibility(View.VISIBLE);
            }
        });
        // 定位初始化
        mLocClient = new LocationClient(mactivity);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        mTitleView = (TitleView) view.findViewById(R.id.titleView);
        mTitleView.setNav_status(true);
        mTitleView.setLeft_imgbtnClickListener(this);
        mTitleView.setLeft_imgbtn1ClickListener(this);
        mTitleView.setTitle("租房");
        mTitleView.setRightImageButton(R.drawable.nav_search_selector, this);


    }


    private void addStatusChange() {
        //地图缩放监听
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                int zoom = (int) mapStatus.zoom;
                if (zoom == zoomScale) {
                    return;
                }
                zoomScale = zoom;
                switch (zoom) {
                    case 12:
                        Toast.makeText(mactivity, "12", Toast.LENGTH_SHORT).show();
                        initOverlayView();
                        break;
                    case 8:
                        Toast.makeText(mactivity, "8", Toast.LENGTH_SHORT).show();
                        break;
                    case 14:
                        Toast.makeText(mactivity, "14", Toast.LENGTH_SHORT).show();
                        mBaiduMap.clear();
                        break;
                }
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });

        //地图覆盖物点击事件监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                int zoom = (int) mBaiduMap.getMapStatus().zoom;
                if (zoom < 13) {
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                            .zoomIn();
//                .zoomBy(zoomScale);
                    mBaiduMap.animateMapStatus(mapStatusUpdate);
                    return false;
                }
                MarkerInfoBean bean = (MarkerInfoBean) marker.getExtraInfo().get("info");
                Intent intent = new Intent(mactivity, RentMapListActivity.class);
                intent.putExtra("data",bean);
                mactivity.startActivity(intent);
                mactivity.overridePendingTransition(R.anim.activity_up,R.anim.activity_down);
                return false;
            }
        });
    }


    //初始化覆盖物
    private void initOverlayView() {
        LatLng point = new LatLng(31.68306, 119.960159);
        LatLng point1 = new LatLng(31.690096, 119.962064);
        ArrayList<LatLng> datas = new ArrayList<LatLng>();
        datas.add(point);
        datas.add(point1);

        //设置Marker数据
        MarkerInfoBean bean1 = new MarkerInfoBean();
        bean1.setAddress("常州武进常州大学");
        bean1.setDesc("这是一所本科学校");
        MarkerInfoBean bean2 = new MarkerInfoBean();
        bean2.setAddress("常州武进信息学院");
        bean2.setDesc("这是一所专科学校");
        ArrayList<MarkerInfoBean> beans = new ArrayList<MarkerInfoBean>();
        beans.add(bean1);
        beans.add(bean2);

        Marker marker = null;
        for (int i = 0; i < 2; i++) {
            View overlayview = View.inflate(mactivity, R.layout.overlayview, null);
            TextView overlayview_tv = (TextView) overlayview.findViewById(R.id.overlayview_tv);

            overlayview_tv.setText(i + 100 + "");
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(overlayview);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions overlayoptions = new MarkerOptions()
                    .position(datas.get(i))
                    .icon(bitmap).zIndex(12 + i * 2);//zIndex不知道什么效果
            //在地图上添加Marker，并显示
            marker = (Marker) mBaiduMap.addOverlay(overlayoptions);
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", beans.get(i));
            marker.setExtraInfo(bundle);
        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
//            if (isFirstLoc) {
//                isFirstLoc = false;
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
//            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    public void dismissMap(){
        if (mMapView != null) {
            mMapView.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            mMapView.onPause();
            mLocClient.stop();
            // 关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
            mMapView.onDestroy();
            mMapView = null;
            mactivity.unregisterReceiver(mReceiver);
        }
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_imgbtn:
                mTitleView.setNav_status(!mTitleView.isNav_status());
                break;
            case R.id.left_imgbtn1:
                mTitleView.setNav_status(!mTitleView.isNav_status());
                break;
            case R.id.right_imgbtn:
//                mBaiduMap.getMapStatus().target;获取屏幕中心点
                LatLng point = new LatLng(31.68306, 119.960159);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                        .newLatLng(point);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                Toast.makeText(mactivity,"搜索",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
