package com.sxy.uclock;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

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
    private WheelView wheelMonth, wheelDay, wheelWeekDay, wheelTime, wheelMin;
    private int mLabel;
    private Serializable mEntity;
    private String[] weekArray = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLabel = getIntent().getIntExtra("lab", -1);
        mEntity = getIntent().getSerializableExtra("entity");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        setSupportActionBar(mBinding.tbAdd);
        mBinding.tbAdd.setNavigationIcon(R.mipmap.toolbar_back_white_36dp);

        int curYear = cal.get(Calendar.YEAR);
        int curMonth = cal.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = cal.get(Calendar.DATE);
        int curWeekDay = cal.get(Calendar.DAY_OF_WEEK);

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
                    ((WorkAndRestDetailsEntity) mEntity).detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
                    int curTime = cal.get(Calendar.HOUR_OF_DAY);
                    int curMin = cal.get(Calendar.MINUTE);
                    String time = (curTime < 10 ? "0" + curTime : curTime) + ":" + (curMin < 10 ? "0" + curMin : curMin);
                    mBinding.tvAddTime.setText(time);
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
        //设置焦点
        mBinding.sblAdd.setFocusable(true);
        mBinding.sblAdd.setFocusableInTouchMode(true);
        mBinding.sblAdd.requestFocus();
        //初始化datePicker
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
        if (isScrolling) {
            return;
        }
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
                ((WorkAndRestDetailsEntity) mEntity).detailsDate.set("");
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
        int curYear = cal.get(Calendar.YEAR);
        int curMonth = cal.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = cal.get(Calendar.DATE);
        int curWeekDay = cal.get(Calendar.DAY_OF_WEEK);
        int curTime = cal.get(Calendar.HOUR_OF_DAY);
        int curMin = cal.get(Calendar.MINUTE);
        View dataPickerView = LayoutInflater.from(this).inflate(R.layout.view_date_picker, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dataPickerView.setLayoutParams(params);
        wheelMonth = (WheelView) dataPickerView.findViewById(R.id.wv_month);
        wheelDay = (WheelView) dataPickerView.findViewById(R.id.wv_day);
        wheelWeekDay = (WheelView) dataPickerView.findViewById(R.id.wv_weekday);
        wheelTime = (WheelView) dataPickerView.findViewById(R.id.wv_time);
        wheelMin = (WheelView) dataPickerView.findViewById(R.id.wv_min);
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
            numericWheelAdapter.setLabel("号");
            wheelDay.setViewAdapter(numericWheelAdapter);
            wheelDay.setCurrentItem(curDate - 1);
            wheelDay.setCyclic(true);//是否可循环滑动
            wheelDay.addScrollingListener(mScrollListener);
        }
        if (wheelWeekDay.getVisibility() == View.VISIBLE) {
            ArrayWheelAdapter<String> arrayWheelAdapter = new ArrayWheelAdapter<>(this, weekArray);
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
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 0, 59, "%02d");
            numericWheelAdapter.setLabel("分");
            wheelMin.setViewAdapter(numericWheelAdapter);
            wheelMin.setCurrentItem(curMin);
            wheelMin.setCyclic(true);//是否可循环滑动
            wheelMin.addScrollingListener(mScrollListener);
        }
        mBinding.layAddDatapicker.addView(dataPickerView);
        mBinding.layAddDatapicker.setVisibility(View.GONE);
    }

    private boolean isScrolling = false;
    private OnWheelScrollListener mScrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
            isScrolling = true;
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            switch (mLabel) {
                case EDIT_WORK_AND_REST:
                case ADD_WORK_AND_REST:
                    initTime();
                    break;
                case ADD_WEEK_PLAN:
                case EDIT_WEEK_PLAN:
                    mBinding.tvAddDate.setText(weekArray[wheelWeekDay.getCurrentItem()]);
                    initTime();
                    break;
                case ADD_MONTH_PLAN:
                case EDIT_MONTH_PLAN:
                    String day = (wheelDay.getCurrentItem() + 1) + "号";
                    mBinding.tvAddDate.setText(day);
                    initTime();
                    break;
                case ADD_IMPORTANT_DATES:
                case EDIT_IMPORTANT_DATES:
                    String data = (wheelMonth.getCurrentItem() + 1) + "月" + (wheelDay.getCurrentItem() + 1) + "号";
                    mBinding.tvAddTime.setText(data);
                    break;
            }
            isScrolling = false;
        }
    };

    private void initTime() {
        int selTime = wheelTime.getCurrentItem() + 1;
        int selMin = wheelMin.getCurrentItem();
        String time = (selTime < 10 ? "0" + selTime : selTime) + ":" + (selMin < 10 ? "0" + selMin : selMin);
        mBinding.tvAddTime.setText(time);
    }

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        if (year % 100 == 0) {
            switch (year % 400) {
                case 0:
                    flag = true;
                    break;
                default:
                    flag = false;
                    break;
            }
        } else {
            switch (year % 4) {
                case 0:
                    flag = true;
                    break;
                default:
                    flag = false;
                    break;
            }
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
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mBinding.etAddDescribe.getWindowToken(), 0);//强制隐藏软键盘
                if (mBinding.layAddDatapicker.getVisibility() == View.GONE) {
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
        if (isScrolling) {
            return;
        }
        super.onBackPressed();
    }
}
