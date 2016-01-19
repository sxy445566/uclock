package com.sxy.uclock;

import android.os.Bundle;
import android.view.View;

import com.sxy.uclock.base.BaseActivity;

public class AddActivity extends BaseActivity {
    public static final int ADD_WORK_AND_REST=0;
    public static final int ADD_WEEK_PLAN=1;
    public static final int ADD_MONTH_PLAN=2;
    public static final int ADD_IMPORTANT_DATES=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public void onClick(View v) {

    }
}
