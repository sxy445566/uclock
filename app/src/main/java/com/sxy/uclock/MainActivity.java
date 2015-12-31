package com.sxy.uclock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import com.sxy.uclock.view.WARTemplateInfoDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener{
    private String[] tabList;
    public ActivityMainBinding mBinding;
    private List<Fragment> fragmentList = new ArrayList<>();
    private WorkAndRestFragment mWARFragment;
    private WeekPlanFragment mWeekPlanFragment;
    private MonthPlanFragment mMonthPlanFragment;
    private ImportantDatesFragment mImportantDatesFragment;
    /**
     * ViewPager缓存页面数目;当前页面的相邻N各页面都会被缓存
     */
    private static int CACHE_PAGERS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //初始化toolbar
        mBinding.tbMain.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mBinding.tbMain);
        mBinding.tbMain.setNavigationIcon(R.mipmap.toolbar_list_white_36dp);
        //初始化fragment
        tabList = this.getResources().getStringArray(R.array.main_tablelayout_array);
        fragmentList.add(mWARFragment=new WorkAndRestFragment());
        fragmentList.add(mWeekPlanFragment=new WeekPlanFragment());
        fragmentList.add(mMonthPlanFragment=new MonthPlanFragment());
        fragmentList.add(mImportantDatesFragment=new ImportantDatesFragment());
        MainTabLayoutAdapter tabAdapter = new MainTabLayoutAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(tabList));
        //初始化viewpager
        mBinding.vpMain.setAdapter(tabAdapter);
        mBinding.vpMain.setOffscreenPageLimit(CACHE_PAGERS);//设置缓存页面数量，相邻的cachePagers个页面都会被缓存
        mBinding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Fragment fragment = fragmentList.get(position);
                if (fragment instanceof WorkAndRestFragment) {
                    if (((WorkAndRestFragment) fragment).getLinearLayoutManager().findLastVisibleItemPosition() < 19) {
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

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        if (id == R.id.action_edit) {
            return true;
        }
        if (id == android.R.id.home) {
            mBinding.dlMain.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        if (ModelUtils.isFastClick()) {
            return;
        }
        switch (v.getId()){
            case R.id.fbtn_main:
                if ( fragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WorkAndRestFragment) {
                    WARTemplateInfoDialog dialog=new WARTemplateInfoDialog(this,R.style.WARTemplateInfoDialogStyle,new WorkAndRestTemplateEntity());
                    dialog.show();

                }
                if ( fragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof WeekPlanFragment) {

                }
                if ( fragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof MonthPlanFragment) {

                }
                if ( fragmentList.get(mBinding.vpMain.getCurrentItem()) instanceof ImportantDatesFragment) {

                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ModelUtils.exitBy2Click(this);
    }
}
