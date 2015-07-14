package com.xx.awjw.awjw.rent.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.xx.awjw.awjw.R;
import com.xx.awjw.awjw.bean.MarkerInfoBean;

/**
 * Created by Administrator on 2015/5/15.
 */
public class RentMapListAdaptor extends BaseAdapter {
    private Context mContext;
    private MarkerInfoBean mMarkerInfoBean;
    private LayoutInflater mInflater;

    public RentMapListAdaptor(Context context, MarkerInfoBean bean) {
        this.mContext = context;
        this.mMarkerInfoBean = bean;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = mInflater.inflate(R.layout.rentmaplist_item,null);
        return contentView;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return mMarkerInfoBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
