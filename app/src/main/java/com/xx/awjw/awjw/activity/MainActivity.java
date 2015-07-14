package com.xx.awjw.awjw.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.rent.fragement.RentFragment;
import com.xx.awjw.awjw.fragment.UsedHouseFragment;
import com.xx.awjw.awjw.view.TabView;



public class MainActivity extends Activity implements View.OnClickListener {
    //密钥    D4:C0:10:1D:8C:EA:80:ED:F5:85:40:CD:37:2B:C2:EA:72:68:60:82;com.xx.awjw.awjw
    private TabView tabView1, tabView2, tabView3, tabView4, tabView5, currentTabView;
    private RentFragment rentfragment;
    private UsedHouseFragment usedhousefragment;

    public  static int RENT = 100;
    public   static int USEDHOUSE = 101;

    public Handler handler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setFragment(getRentfragment());

    }


    public RentFragment getRentfragment() {
        if (rentfragment == null) {
            rentfragment = new RentFragment();
        }
        return rentfragment;
    }
    public UsedHouseFragment getUsedHouseFragment() {
        if (usedhousefragment == null) {
            usedhousefragment = new UsedHouseFragment();
        }
        return usedhousefragment;
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        transaction.commit();
    }

    private void initView() {
        tabView1 = (TabView) findViewById(R.id.tabview1);
        tabView1.setOnClickListener(this);
        tabView2 = (TabView) findViewById(R.id.tabview2);
        tabView2.setOnClickListener(this);
        tabView3 = (TabView) findViewById(R.id.tabview3);
        tabView3.setOnClickListener(this);
        tabView4 = (TabView) findViewById(R.id.tabview4);
        tabView4.setOnClickListener(this);
        tabView5 = (TabView) findViewById(R.id.tabview5);
        tabView5.setOnClickListener(this);
        tabView1.setChecked(true);
        currentTabView = tabView1;

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 100:
                        setFragment(getRentfragment());
                        changeTab(tabView1);
                        break;
                    case 101:
                        setFragment(getUsedHouseFragment());
                        changeTab(tabView2);
                        break;
                }
            }
        };
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tabview1:
                getUsedHouseFragment().dismissMap();
                handler.sendEmptyMessageDelayed(RENT,1200);
//                setFragment(getRentfragment());
//                changeTab(tabView1);
                break;
            case R.id.tabview2:
                getRentfragment().dismissMap();
                handler.sendEmptyMessageDelayed(USEDHOUSE, 1200);
//                setFragment(getUsedHouseFragment());
//                changeTab(tabView2);
                break;
            case R.id.tabview3:
                changeTab(tabView3);
                break;
            case R.id.tabview4:
                changeTab(tabView4);
                break;
            case R.id.tabview5:
                changeTab(tabView5);
                break;

        }
    }

    //切换tab
    private void changeTab(TabView tabView) {
        if (currentTabView != null) {
            currentTabView.setChecked(false);
        }
        tabView.setChecked(true);
        currentTabView = tabView;
    }

}
