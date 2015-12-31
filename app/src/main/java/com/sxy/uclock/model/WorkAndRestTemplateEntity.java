package com.sxy.uclock.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.util.Observable;

/**
 * Created by xf on 2015/8/20.
 */
public class WorkAndRestTemplateEntity extends Observable{
    public final ObservableInt templateID=new ObservableInt();
    public final ObservableField<String> templateName=new ObservableField<>();
    public final ObservableField<String> templateDays=new ObservableField<>();
    public final ObservableBoolean templateIsUsing=new ObservableBoolean();

}
