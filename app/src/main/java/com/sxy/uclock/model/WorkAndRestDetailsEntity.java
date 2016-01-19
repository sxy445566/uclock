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
    public WorkAndRestDetailsEntity(){
        detailsIsDelModel.set(false);
        detailsIsDelChecked.set(false);
    }
    public final ObservableInt detailsID=new ObservableInt();
    public final ObservableInt templateID=new ObservableInt();//模版ID
    public final ObservableField<String> detailsTime=new ObservableField<>();//作息时间
    public final ObservableField<String> detailsDescribe=new ObservableField<>();//作息描述
    public final ObservableBoolean detailsIsUsing=new ObservableBoolean();//是否开启
    public final ObservableBoolean detailsIsDelModel=new ObservableBoolean();//删除模式
    public final ObservableBoolean detailsIsDelChecked=new ObservableBoolean();
}
