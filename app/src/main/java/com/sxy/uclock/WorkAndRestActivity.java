package com.sxy.uclock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sxy.uclock.adapter.WorkAndRestListAdapter;
import com.sxy.uclock.base.BaseActivity;
import com.sxy.uclock.base.BaseRecyclerViewAdapter;
import com.sxy.uclock.databinding.ActivityWorkAndRestBinding;
import com.sxy.uclock.db.WorkAndRestDetails;
import com.sxy.uclock.model.WorkAndRestDetailsBLL;
import com.sxy.uclock.view.BottomPopUpMenuDialog;
import com.sxy.uclock.view.MyMaterialDialog;
import com.sxy.uclock.view.SwipeBackLayout;

import java.util.ArrayList;

public class WorkAndRestActivity extends BaseActivity implements SwipeBackLayout.OnSildingFinishListener, BottomPopUpMenuDialog.OnDialogButtonClick, BaseRecyclerViewAdapter.RecyclerViewAdapterListener, BaseRecyclerViewAdapter.RecyclerViewAdapterDelModelListener {
    private ActivityWorkAndRestBinding mBinding;
    private ArrayList<WorkAndRestDetails> mList;
    private ArrayList<WorkAndRestDetails> mDelList;
    private WorkAndRestListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private BottomPopUpMenuDialog mListOnLongClickDialog;
    private long mTemplateID;
    private boolean mIsDelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_and_rest);
        //初始化toolbar
        mBinding.tbWorkAndRest.setTitle(R.string.title_activity_work_and_rest);
        setSupportActionBar(mBinding.tbWorkAndRest);
        mBinding.tbWorkAndRest.setNavigationIcon(R.mipmap.toolbar_back_white_36dp);
        //初始化SwipeBackLayout
        mBinding.sblWorkAndRest.setOnSildingFinishListener(this);
        //初始化RecyclerView
        mLinearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvWorkAndRest.setHasFixedSize(true);
        mBinding.rvWorkAndRest.setLayoutManager(mLinearLayoutManager);
        mBinding.rvWorkAndRest.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvWorkAndRest.setAdapter(mAdapter);
        //初始化删除按钮
        mBinding.cvWorkAndRestDel.setOnClickListener(this);
    }

    private void initDate() {
        mTemplateID = getIntent().getLongExtra("templateID",0);
        mList = WorkAndRestDetailsBLL.getWarListByTemplateIDAndDetailsType(mTemplateID,WorkAndRestDetails.DETAILS_TYPE_WAR);
        mAdapter = new WorkAndRestListAdapter(this, mList, this, this);
        mIsDelModel = false;
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

        switch (id) {
            case android.R.id.home:
                if (mIsDelModel) {
                    quitDeleteModel();
                } else {
                    this.onBackPressed();
                }
                break;
            case R.id.action_add:
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("lab", AddActivity.ADD_WORK_AND_REST);
                intent.putExtra("templateID", mTemplateID);
                startActivityForResult(intent, AddActivity.ADD_WORK_AND_REST);
                break;
            case R.id.action_delete:
                showDeleteModel();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteModel() {
        mBinding.tbWorkAndRest.setTitle(R.string.action_delete);
        for (int i = 0; i < mBinding.tbWorkAndRest.getMenu().size(); i++) {
            mBinding.tbWorkAndRest.getMenu().getItem(i).setVisible(false);
        }
        mBinding.sblWorkAndRest.setIsDelModel(true);
        mBinding.cvWorkAndRestDel.setVisibility(View.VISIBLE);
        mDelList = new ArrayList<>();
        for (WorkAndRestDetails entity : mList) {
            entity.setDetailsIsDelModel(true);
        }
        mAdapter.notifyDataSetChanged();
        mIsDelModel = true;
    }

    private void quitDeleteModel() {
        mBinding.tbWorkAndRest.setTitle(R.string.title_activity_work_and_rest);
        for (int i = 0; i < mBinding.tbWorkAndRest.getMenu().size(); i++) {
            mBinding.tbWorkAndRest.getMenu().getItem(i).setVisible(true);
        }
        mBinding.sblWorkAndRest.setIsDelModel(false);
        mBinding.cvWorkAndRestDel.setVisibility(View.GONE);
        for (WorkAndRestDetails entity : mList) {
            entity.setDetailsIsDelModel(false);
            entity.setDetailsIsDelChecked(false);
        }
        mAdapter.notifyDataSetChanged();
        mIsDelModel = false;
        mDelList.clear();
        mDelList = null;
    }

    private void batchDelete() {
        if (mDelList == null || mDelList.size() == 0) {
            showToast(getString(R.string.war_details_del_null));
            return;
        }
        MyMaterialDialog.createMyMaterialDialog(this)
                .title(R.string.dialog_del_title)
                .content(R.string.war_details_del_dialog_content)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which.name().equals(DialogAction.POSITIVE.name())) {
                            if (WorkAndRestDetailsBLL.delDetails(mDelList)) {
                                mAdapter.notifyDataSetChanged();
                                showToast(getString(R.string.dialog_del_success));
                            }
                        }
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_work_and_rest_del:
                batchDelete();
                break;
        }
    }

    @Override
    public void onSildingFinish() {
        this.onBackPressed();
    }

    @Override
    public void onRecyclerViewItemDelModelClick(int position) {
        View view = mLinearLayoutManager.findViewByPosition(position);
        CheckBox cbDel = (CheckBox) view.findViewById(R.id.cb_details_item_del);
        cbDel.toggle();
        WorkAndRestDetails entity = mList.get(position);
        if (cbDel.isChecked()) {
            entity.setDetailsIsDelChecked(true);
            mDelList.add(entity);
        } else {
            entity.setDetailsIsDelChecked(false);
            if (mDelList.contains(entity)) {
                mDelList.remove(entity);
            }
        }
    }

    @Override
    public void onRecyclerViewItemDelModelLongClick(int position) {

    }

    @Override
    public void onRecyclerViewItemClick(int position) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("lab", AddActivity.EDIT_WORK_AND_REST);
        intent.putExtra("entity", mList.get(position));
        startActivityForResult(intent, AddActivity.EDIT_WORK_AND_REST);
    }

    @Override
    public void onRecyclerViewItemLongClick(int position) {
        mListOnLongClickDialog = new BottomPopUpMenuDialog(this, R.style.ListOnLongClickDialogStyle, this, position, BottomPopUpMenuDialog.TAG_LONG_CLICK);
        mListOnLongClickDialog.show();
    }

    @Override
    public void OnButtonClick(int btnType, final int position, int viewTag) {
        switch (btnType) {
            case BottomPopUpMenuDialog.EDIT_BUTTON:
                if (viewTag == BottomPopUpMenuDialog.TAG_LONG_CLICK) {
                    Intent intent = new Intent(this, AddActivity.class);
                    intent.putExtra("lab", AddActivity.EDIT_WORK_AND_REST);
                    intent.putExtra("entity", mList.get(position));
                    startActivityForResult(intent, AddActivity.EDIT_WORK_AND_REST);
                } else if (viewTag == BottomPopUpMenuDialog.TAG_MENU_CLICK) {
                    Intent intent = new Intent(this, AddActivity.class);
                    intent.putExtra("lab", AddActivity.ADD_WORK_AND_REST);
                    intent.putExtra("templateID", mTemplateID);
                    startActivityForResult(intent, AddActivity.ADD_WORK_AND_REST);
                }
                break;
            case BottomPopUpMenuDialog.DEL_BUTTON:
                if (viewTag == BottomPopUpMenuDialog.TAG_LONG_CLICK) {
                    MyMaterialDialog.createMyMaterialDialog(this)
                            .title(R.string.dialog_del_title)
                            .content(R.string.war_details_del_dialog_content)
                            .positiveText(R.string.action_sure)
                            .negativeText(R.string.action_cancel)
                            .onAny(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which.name().equals(DialogAction.POSITIVE.name())) {
                                        if (WorkAndRestDetailsBLL.delDetail(mList.get(position))) {
                                            mAdapter.notifyDataSetChanged();
                                            showToast(getString(R.string.dialog_del_success));
                                        }
                                    }
                                }
                            })
                            .show();
                } else if (viewTag == BottomPopUpMenuDialog.TAG_MENU_CLICK) {
                    showDeleteModel();
                }
                break;
            case BottomPopUpMenuDialog.CANCEL_BUTTON:
                mListOnLongClickDialog.dismiss();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mIsDelModel) {
            quitDeleteModel();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (!mIsDelModel) {
                BottomPopUpMenuDialog dialog = new BottomPopUpMenuDialog(this, R.style.ListOnLongClickDialogStyle, this, 0, BottomPopUpMenuDialog.TAG_MENU_CLICK);
                dialog.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==1) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
