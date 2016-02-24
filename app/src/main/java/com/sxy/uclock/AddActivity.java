package com.sxy.uclock;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.byl.datapicker.wheelview.OnWheelScrollListener;
import com.byl.datapicker.wheelview.WheelView;
import com.byl.datapicker.wheelview.adapter.ArrayWheelAdapter;
import com.byl.datapicker.wheelview.adapter.NumericWheelAdapter;
import com.sxy.uclock.base.BaseActivity;
import com.sxy.uclock.databinding.ActivityAddBinding;
import com.sxy.uclock.model.WorkAndRestDetailsBLL;
import com.sxy.uclock.model.WorkAndRestDetailsEntity;
import com.sxy.uclock.view.SwipeBackLayout;

import java.io.Serializable;
import java.util.Calendar;

public class AddActivity extends BaseActivity implements SwipeBackLayout.OnSildingFinishListener {
    public static final int ADD_WORK_AND_REST = 10;
    public static final int EDIT_WORK_AND_REST = 11;
    public static final int ADD_WEEK_PLAN = 20;
    public static final int EDIT_WEEK_PLAN = 21;
    public static final int ADD_MONTH_PLAN = 30;
    public static final int EDIT_MONTH_PLAN = 31;
    public static final int ADD_IMPORTANT_DATES = 40;
    public static final int EDIT_IMPORTANT_DATES = 41;
    private ActivityAddBinding mBinding;
    private int mLabel;
    private Serializable mEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLabel = getIntent().getIntExtra("lab", -1);
        mEntity = getIntent().getSerializableExtra("entity");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        setSupportActionBar(mBinding.tbAdd);
        mBinding.tbAdd.setNavigationIcon(R.mipmap.toolbar_back_white_36dp);
        switch (mLabel) {
            case EDIT_WORK_AND_REST:
                if (mEntity != null) {
                    Log.d("warAddActivity", ((WorkAndRestDetailsEntity) mEntity).detailsID.get() + "");
                    Log.d("warAddActivity", ((WorkAndRestDetailsEntity) mEntity).detailsDescribe.get());
                    mBinding.tvAddTime.setText(((WorkAndRestDetailsEntity) mEntity).detailsTime.get());
                    if (((WorkAndRestDetailsEntity) mEntity).detailsIsUsing.get()) {
                        mBinding.ivAddIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
                    } else {
                        mBinding.ivAddIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
                    }
                    mBinding.etAddDescribe.setText(((WorkAndRestDetailsEntity) mEntity).detailsDescribe.get());
                }
            case ADD_WORK_AND_REST:
                int templateID = getIntent().getIntExtra("templateID", 0);
                mBinding.tbAdd.setTitle(R.string.title_activity_work_and_rest);
                mBinding.tvAddDate.setVisibility(View.GONE);
                if (mEntity == null) {
                    mEntity = new WorkAndRestDetailsEntity();
                    ((WorkAndRestDetailsEntity) mEntity).templateID.set(templateID);
                    mBinding.tvAddTime.setText("07:30");
                    mBinding.ivAddIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
                }

                break;
            case ADD_WEEK_PLAN:
                break;
            case ADD_MONTH_PLAN:
                break;
            case ADD_IMPORTANT_DATES:
                break;
        }
        mBinding.cvAddTime.setOnClickListener(this);
        mBinding.ivAddIsusing.setOnClickListener(this);
        mBinding.sblAdd.setOnSildingFinishListener(this);
        initDataPicker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            saveEntity();
        }
        if (id == android.R.id.home) {
            setResult(1);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveEntity() {
        switch (mLabel) {
            case EDIT_WORK_AND_REST:
                setEntity();
                WorkAndRestDetailsBLL.editDetail((WorkAndRestDetailsEntity) mEntity);
                break;
            case ADD_WORK_AND_REST:
                setEntity();
                WorkAndRestDetailsBLL.addDetail((WorkAndRestDetailsEntity) mEntity);
                break;
            case ADD_WEEK_PLAN:
                break;
            case ADD_MONTH_PLAN:
                break;
            case ADD_IMPORTANT_DATES:
                break;
        }
        setResult(1);
        this.finish();
    }

    private void setEntity() {
        switch (mLabel) {
            case EDIT_WORK_AND_REST:
            case ADD_WORK_AND_REST:
                ((WorkAndRestDetailsEntity) mEntity).detailsTime.set(mBinding.tvAddTime.getText().toString());
                ((WorkAndRestDetailsEntity) mEntity).detailsDescribe.set(mBinding.etAddDescribe.getText().toString());
                break;
            case ADD_WEEK_PLAN:
                break;
            case ADD_MONTH_PLAN:
                break;
            case ADD_IMPORTANT_DATES:
                break;
        }
    }

    private void initDataPicker() {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        int curWeekDay = c.get(Calendar.DAY_OF_WEEK);
        int curTime = c.get(Calendar.HOUR_OF_DAY);
        int curMin = c.get(Calendar.MINUTE);
        View dataPickerView = LayoutInflater.from(this).inflate(R.layout.view_date_picker, null);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dataPickerView.setLayoutParams(params);
        WheelView wheelMonth = (WheelView) dataPickerView.findViewById(R.id.wv_month);
        WheelView wheelDay = (WheelView) dataPickerView.findViewById(R.id.wv_day);
        WheelView wheelWeekDay = (WheelView) dataPickerView.findViewById(R.id.wv_weekday);
        WheelView wheelTime = (WheelView) dataPickerView.findViewById(R.id.wv_time);
        WheelView wheelMin = (WheelView) dataPickerView.findViewById(R.id.wv_min);
        switch (mLabel) {
            case EDIT_WORK_AND_REST:
            case ADD_WORK_AND_REST:
                wheelMonth.setVisibility(View.GONE);
                wheelDay.setVisibility(View.GONE);
                wheelWeekDay.setVisibility(View.GONE);
                break;
            case ADD_WEEK_PLAN:
            case EDIT_WEEK_PLAN:
                wheelMonth.setVisibility(View.GONE);
                wheelDay.setVisibility(View.GONE);
                break;
            case ADD_MONTH_PLAN:
            case EDIT_MONTH_PLAN:
                wheelMonth.setVisibility(View.GONE);
                wheelWeekDay.setVisibility(View.GONE);
                break;
            case ADD_IMPORTANT_DATES:
            case EDIT_IMPORTANT_DATES:
                wheelWeekDay.setVisibility(View.GONE);
                wheelTime.setVisibility(View.GONE);
                wheelMin.setVisibility(View.GONE);
                break;
        }
        if (wheelMonth.getVisibility() == View.VISIBLE) {
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 12, "%02d");
            numericWheelAdapter.setLabel("月");
            wheelMonth.setViewAdapter(numericWheelAdapter);
            wheelMonth.setCurrentItem(curMonth - 1);
            wheelMonth.setCyclic(true);//是否可循环滑动
            wheelMonth.addScrollingListener(mScrollListener);
        }
        if (wheelDay.getVisibility() == View.VISIBLE) {
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(curYear, curMonth), "%02d");
            numericWheelAdapter.setLabel("日");
            wheelDay.setViewAdapter(numericWheelAdapter);
            wheelDay.setCurrentItem(curDate - 1);
            wheelDay.setCyclic(true);//是否可循环滑动
            wheelDay.addScrollingListener(mScrollListener);
        }
        if (wheelWeekDay.getVisibility() == View.VISIBLE) {
            ArrayWheelAdapter<String> arrayWheelAdapter = new ArrayWheelAdapter<>(this, new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"});
            wheelWeekDay.setViewAdapter(arrayWheelAdapter);
            wheelWeekDay.setCurrentItem(curWeekDay - 1);
            wheelWeekDay.setCyclic(true);//是否可循环滑动
            wheelWeekDay.addScrollingListener(mScrollListener);
        }
        if (wheelTime.getVisibility() == View.VISIBLE) {
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 24, "%02d");
            numericWheelAdapter.setLabel("时");
            wheelTime.setViewAdapter(numericWheelAdapter);
            wheelTime.setCurrentItem(curTime - 1);
            wheelTime.setCyclic(true);//是否可循环滑动
            wheelTime.addScrollingListener(mScrollListener);
        }
        if (wheelMin.getVisibility() == View.VISIBLE) {
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 0, 60, "%02d");
            numericWheelAdapter.setLabel("分");
            wheelMin.setViewAdapter(numericWheelAdapter);
            wheelMin.setCurrentItem(curMin);
            wheelMin.setCyclic(true);//是否可循环滑动
            wheelMin.addScrollingListener(mScrollListener);
        }
        mBinding.layAddDatapicker.addView(dataPickerView);
        mBinding.layAddDatapicker.setVisibility(View.GONE);
    }

    private OnWheelScrollListener mScrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {

        }
    };

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_isusing:
                if (mEntity instanceof WorkAndRestDetailsEntity) {
                    if (((WorkAndRestDetailsEntity) mEntity).detailsIsUsing.get()) {
                        ((WorkAndRestDetailsEntity) mEntity).detailsIsUsing.set(false);
                        mBinding.ivAddIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
                    } else {
                        ((WorkAndRestDetailsEntity) mEntity).detailsIsUsing.set(true);
                        mBinding.ivAddIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
                    }
                }
                break;
            case R.id.cv_add_time:
                if (mBinding.layAddDatapicker.getVisibility()==View.GONE) {
                    mBinding.layAddDatapicker.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onSildingFinish() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
    }
}
