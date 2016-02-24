package com.sxy.uclock;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxy.uclock.adapter.MainTabLayoutAdapter;
import com.sxy.uclock.base.BaseActivity;
import com.sxy.uclock.base.BaseFragment;
import com.sxy.uclock.databinding.ActivityMainBinding;
import com.sxy.uclock.fragment.ImportantDatesFragment;
import com.sxy.uclock.fragment.MonthPlanFragment;
import com.sxy.uclock.fragment.WeekPlanFragment;
import com.sxy.uclock.fragment.WorkAndRestFragment;
import com.sxy.uclock.model.WorkAndRestTemplateEntity;
import com.sxy.uclock.tools.ModelUtils;
import com.sxy.uclock.view.BottomPopUpMenuDialog;
import com.sxy.uclock.view.WARTemplateInfoDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener,BottomPopUpMenuDialog.OnDialogButtonClick{
    private String[] mTabList;
    private ActivityMainBinding mBinding;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private WorkAndRestFragment mWARFragment;
    private WeekPlanFragment mWeekPlanFragment;
    private MonthPlanFragment mMonthPlanFragment;
    private ImportantDatesFragment mImportantDatesFragment;
    private boolean mIsDelModel;
    /**
     * ViewPager缓存页面数目;当前页面的相邻N各页面都会被缓存
     */
    private static int CACHE_PAGERS = 4;

    public ActivityMainBinding getMainBinding(){
        return mBinding;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDelModel = false;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //初始化toolbar
        mBinding.tbMain.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mBinding.tbMain);
        mBinding.tbMain.setNavigationIcon(R.mipmap.toolbar_list_white_36dp);
        //初始化fragment
        mTabList = this.getResources().getStringArray(R.array.main_tablelayout_array);
        mFragmentList.add(mWARFragment = new WorkAndRestFragment());
        mFragmentList.add(mWeekPlanFragment = new WeekPlanFragment());
        mFragmentList.add(mMonthPlanFragment = new MonthPlanFragment());
        mFragmentList.add(mImportantDatesFragment = new ImportantDatesFragment());
        MainTabLayoutAdapter tabAdapter = new MainTabLayoutAdapter(getSupportFragmentManager(), mFragmentList, Arrays.asList(mTabList));
        //初始化viewpager
        mBinding.vpMain.setAdapter(tabAdapter);
        mBinding.vpMain.setOffscreenPageLimit(CACHE_PAGERS);//设置缓存页面数量，相邻的cachePagers个页面都会被缓存
        mBinding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mIsDelModel) {
                } else {
                    Fragment fragment = mFragmentList.get(position);
                    if (fragment instanceof WorkAndRestFragment) {
                        if (((WorkAndRestFragment) fragment).getLinearLayoutManager().findLastVisibleItemPosition() < ((WorkAndRestFragment) fragment).getTemplateList().size()) {
                            mBinding.fbtnMain.setVisibility(View.VISIBLE);
                        } else {
                            mBinding.fbtnMain.setVisibility(View.GONE);
                        }
                    }
                    if (fragment instanceof WeekPlanFragment) {

                        mBinding.fbtnMain.setVisibility(View.VISIBLE);
                    }
                    if (fragment instanceof MonthPlanFragment) {

                        mBinding.fbtnMain.setVisibility(View.VISIBLE);
                    }
                    if (fragment instanceof ImportantDatesFragment) {

                        mBinding.fbtnMain.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //初始化TabLayout
        mBinding.tlMain.setupWithViewPager(mBinding.vpMain);
        mBinding.tlMain.setTabsFromPagerAdapter(tabAdapter);
        mBinding.tlMain.setTabMode(TabLayout.MODE_FIXED);
        //初始化navigationView
        mBinding.nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                //关闭抽屉侧滑菜单
                mBinding.dlMain.closeDrawers();
                return true;
            }
        });
        ImageView ivHeaderAvatar = (ImageView) mBinding.nvMain.getHeaderView(0).findViewById(R.id.iv_header_avatar);
        TextView tvHeaderName = (TextView) mBinding.nvMain.getHeaderView(0).findViewById(R.id.tv_header_name);
        ivHeaderAvatar.setImageResource(R.mipmap.aaa);
        tvHeaderName.setText("aaaa");
        //初始化floatingActionButton
        mBinding.fbtnMain.setOnClickListener(this);
        //初始化删除按钮
        mBinding.cvMainDel.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            showDeleteModel();
        }
        if (id == android.R.id.home) {
            if (mIsDelModel) {
                quitDeleteModel();
            } else {
                mBinding.dlMain.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteModel() {
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WorkAndRestFragment) {
            mWARFragment.startDelete();
            onDelete();
        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WeekPlanFragment) {

        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof MonthPlanFragment) {

        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof ImportantDatesFragment) {

        }
    }

    private void quitDeleteModel() {
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WorkAndRestFragment) {
            mWARFragment.quitDelete();
            onQuitDelete();
        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WeekPlanFragment) {

        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof MonthPlanFragment) {

        }
        if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof ImportantDatesFragment) {

        }
    }

    private void onDelete() {
        mBinding.tlMain.setVisibility(View.GONE);
        mBinding.cvMainDel.setVisibility(View.VISIBLE);
        mBinding.fbtnMain.setVisibility(View.GONE);
        mBinding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mBinding.tbMain.setTitle(R.string.action_delete);
        mBinding.tbMain.setNavigationIcon(R.mipmap.toolbar_back_white_36dp);
        for (int i = 0; i < mBinding.tbMain.getMenu().size(); i++) {
            mBinding.tbMain.getMenu().getItem(i).setVisible(false);
        }
        mBinding.vpMain.setScanScroll(false);
        mIsDelModel = true;
    }

    private void onQuitDelete() {
        mBinding.tlMain.setVisibility(View.VISIBLE);
        mBinding.cvMainDel.setVisibility(View.GONE);
        mBinding.fbtnMain.setVisibility(View.VISIBLE);
        mBinding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mBinding.tbMain.setTitle(getResources().getString(R.string.app_name));
        mBinding.tbMain.setNavigationIcon(R.mipmap.toolbar_list_white_36dp);
        for (int i = 0; i < mBinding.tbMain.getMenu().size(); i++) {
            mBinding.tbMain.getMenu().getItem(i).setVisible(true);
        }
        mBinding.vpMain.setScanScroll(true);
        mIsDelModel = false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        if (ModelUtils.isFastClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.fbtn_main:
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WorkAndRestFragment) {
                    WARTemplateInfoDialog dialog = new WARTemplateInfoDialog(this, R.style.WARTemplateInfoDialogStyle, new WorkAndRestTemplateEntity());
                    dialog.show();

                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WeekPlanFragment) {

                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof MonthPlanFragment) {

                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof ImportantDatesFragment) {

                }
                break;
            case R.id.cv_main_del:
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WorkAndRestFragment) {
                    mWARFragment.batchDelete();
                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WeekPlanFragment) {

                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof MonthPlanFragment) {

                }
                if (mFragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof ImportantDatesFragment) {

                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_MENU){
            if (!mIsDelModel) {
                BottomPopUpMenuDialog dialog = new BottomPopUpMenuDialog(this, R.style.ListOnLongClickDialogStyle, this,0,BottomPopUpMenuDialog.TAG_MENU_CLICK);
                dialog.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mIsDelModel) {
            quitDeleteModel();
        } else {
            ModelUtils.exitBy2Click(this);
        }
    }

    @Override
    public void OnButtonClick(int btnType, int position,int viewTag) {
        switch (btnType){
            case BottomPopUpMenuDialog.DEL_BUTTON:
                showDeleteModel();
                break;
            case BottomPopUpMenuDialog.EDIT_BUTTON:
                WARTemplateInfoDialog dialog = new WARTemplateInfoDialog(this, R.style.WARTemplateInfoDialogStyle, new WorkAndRestTemplateEntity());
                dialog.show();
                break;
        }
    }
}
