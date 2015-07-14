package com.xx.awjw.awjw.bean;

/**
 * Created by Administrator on 2015/5/12.
 */
public enum ZoomScaleEnum {

    ZoomScalelow(12), ZoomScalelittlelow(8), ZoomScalelarge(14),ZoomScaleMax(16),ZoomScaleMix(4);

    private int _value;

    private ZoomScaleEnum(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}