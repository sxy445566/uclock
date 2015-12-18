package com.sxy.uclock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xf on 2015/8/17.
 */
public class MainTabLayoutAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragmentList;
    private List<String> titleList;
    public MainTabLayoutAdapter(FragmentManager fm, List<Fragment> pFragmentList, List<String> pTitleList) {
        super(fm);
        fragmentList=pFragmentList;
        titleList=pTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
