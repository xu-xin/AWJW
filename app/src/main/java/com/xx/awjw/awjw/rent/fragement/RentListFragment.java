package com.xx.awjw.awjw.rent.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.activity.MainActivity;

/**
 * Created by Administrator on 2015/8/14.
 */
public class RentListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private SwipeRefreshLayout option_srl;
    private Handler mHandler = new Handler();
    private LinearLayout ll_sort;
    private TextView tv_sort;
    private PopupWindow mPopupWindow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rentlistfragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        option_srl = (SwipeRefreshLayout) view.findViewById(R.id.option_srl);
        option_srl.setOnRefreshListener(this);
        option_srl.setColorSchemeResources(R.color.orange);

        ll_sort = (LinearLayout) view.findViewById(R.id.ll_sort);
        ll_sort.setOnClickListener(this);
        tv_sort = (TextView) view.findViewById(R.id.tv_sort);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                option_srl.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_sort:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow(){
        View view = View.inflate(getActivity(),R.layout.pop_sort,null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        view.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in));
        lv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.pop_scale));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        if (mPopupWindow == null){
            mPopupWindow = new PopupWindow();
            mPopupWindow.setWidth(option_srl.getWidth());
            mPopupWindow.setHeight(option_srl.getHeight());
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);
        }

        mPopupWindow.setContentView(view);
        mPopupWindow.showAsDropDown(ll_sort);
        mPopupWindow.update();
    }
}
