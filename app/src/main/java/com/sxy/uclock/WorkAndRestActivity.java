package com.sxy.uclock;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sxy.uclock.base.BaseActivity;
import com.sxy.uclock.base.BaseFragment;
import com.sxy.uclock.databinding.ActivityWorkAndRestBinding;
import com.sxy.uclock.fragment.AddWARTemplateFragment;
import com.sxy.uclock.fragment.DetailsWARTemplateFragment;
import com.sxy.uclock.view.SwipeBackLayout;

public class WorkAndRestActivity extends BaseActivity implements SwipeBackLayout.OnSildingFinishListener, BaseFragment.OnFragmentInteractionListener {
    private FragmentManager mFragmentManager;
    private ActivityWorkAndRestBinding mBinding;
    private Fragment mFragment;
    private String mLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_and_rest);
        mBinding.tbWorkAndRest.setTitle(R.string.title_activity_work_and_rest);
        setSupportActionBar(mBinding.tbWorkAndRest);
        mBinding.tbWorkAndRest.setNavigationIcon(R.mipmap.toolbar_back_white_36dp);
        mBinding.sblWorkAndRest.setOnSildingFinishListener(this);
        initFragment();
    }

    private void initFragment() {
        mLabel = getIntent().getStringExtra("lab");
        if (mLabel != null && !"".equals(mLabel)) {
            if (mLabel.equals("add")) {
                mFragment = new AddWARTemplateFragment();
            } else if (mLabel.equals("details")) {
                mFragment = new DetailsWARTemplateFragment();
            }
        } else {
              mFragment = new DetailsWARTemplateFragment();
        }
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_work_and_rest, mFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_work_and_rest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSildingFinish() {
        this.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
