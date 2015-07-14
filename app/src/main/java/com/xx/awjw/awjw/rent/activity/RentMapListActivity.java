package com.xx.awjw.awjw.rent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.RoomDetail.activity.RoomDetailActivity;
import com.xx.awjw.awjw.bean.MarkerInfoBean;
import com.xx.awjw.awjw.rent.adaptor.RentMapListAdaptor;


/**
 * Created by Administrator on 2015/5/15.
 */
public class RentMapListActivity extends Activity implements View.OnClickListener {

    private TextView rentmaplist_tv;
    private ListView rentmaplist_lv;
    private RentMapListAdaptor rentMapListAdaptor;
    private LinearLayout up_ll;
    private LinearLayout rentmaplist_pb;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            rentmaplist_pb.setVisibility(View.INVISIBLE);
            rentmaplist_lv.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rentmaplist);

        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        MarkerInfoBean bean = (MarkerInfoBean) bundle.get("data");

        up_ll = (LinearLayout) findViewById(R.id.up_ll);
        rentmaplist_pb = (LinearLayout) findViewById(R.id.rentmaplist_pb);
//        up_ll.setBackgroundColor(getResources().getColor(R.color.transparent_gray));
        rentmaplist_tv = (TextView) findViewById(R.id.rentmaplist_tv);
        rentmaplist_lv = (ListView) findViewById(R.id.rentmaplist_lv);

        rentmaplist_tv.setOnClickListener(this);
        up_ll.setOnClickListener(this);

        rentMapListAdaptor = new RentMapListAdaptor(this, bean);

        rentmaplist_lv.setAdapter(rentMapListAdaptor);
        rentmaplist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MarkerInfoBean bean = (MarkerInfoBean) adapterView.getItemAtPosition(i);
                Toast.makeText(RentMapListActivity.this, bean.getDesc(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RentMapListActivity.this, RoomDetailActivity.class);
                startActivity(intent);
            }
        });

        handler.sendEmptyMessageDelayed(1, 1000);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rentmaplist_tv:
            case R.id.up_ll:
                this.finish();
                this.overridePendingTransition(R.anim.activity_up, R.anim.activity_down);
                break;
        }
    }
}
