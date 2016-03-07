package com.sxy.uclock.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created by Administrator on 2016/1/18.
 */
public class WorkAndRestDetailsEntity extends Observable implements Serializable{
    public static  final int DETAILS_TYPE_WAR=1;
    public static  final int DETAILS_TYPE_WEEK=2;
    public static  final int DETAILS_TYPE_MONTH=3;
    public static  final int DETAILS_TYPE_IMPORTANT=4;
    public WorkAndRestDetailsEntity(){
        detailsIsUsing.set(true);
        detailsIsDelModel.set(false);
        detailsIsDelChecked.set(false);
    }
    public final ObservableInt detailsID=new ObservableInt();
    public final ObservableInt templateID=new ObservableInt();//模版ID
    public final ObservableInt detailsType=new ObservableInt();//作息类型 1代表每日作息 2代表周计划 3代表月计划 4代表重要日期
    public final ObservableField<String> detailsDate=new ObservableField<>();//作息日期
    public final ObservableField<String> detailsTime=new ObservableField<>();//作息时间
    public final ObservableField<String> detailsDescribe=new ObservableField<>();//作息描述
    public final ObservableBoolean detailsIsUsing=new ObservableBoolean();//是否开启
    public final ObservableBoolean detailsIsDelModel=new ObservableBoolean();//删除模式
    public final ObservableBoolean detailsIsDelChecked=new ObservableBoolean();
}
