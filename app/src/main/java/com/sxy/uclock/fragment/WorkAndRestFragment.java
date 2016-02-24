package com.sxy.uclock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sxy.uclock.MainActivity;
import com.sxy.uclock.R;
import com.sxy.uclock.WorkAndRestActivity;
import com.sxy.uclock.adapter.WorkAndRestTemplateListAdapter;
import com.sxy.uclock.base.BaseFragment;
import com.sxy.uclock.base.BaseRecyclerViewAdapter;
import com.sxy.uclock.model.WorkAndRestTemplateBLL;
import com.sxy.uclock.model.WorkAndRestTemplateEntity;
import com.sxy.uclock.view.BottomPopUpMenuDialog;
import com.sxy.uclock.view.BottomPopUpMenuDialog.OnDialogButtonClick;
import com.sxy.uclock.view.MyMaterialDialog;
import com.sxy.uclock.view.WARTemplateInfoDialog;

import java.util.ArrayList;


public class WorkAndRestFragment extends BaseFragment implements OnDialogButtonClick, BaseRecyclerViewAdapter.RecyclerViewAdapterListener, BaseRecyclerViewAdapter.RecyclerViewAdapterDelModelListener {
    private RecyclerView rvWorkAndRestList;
    private ArrayList<WorkAndRestTemplateEntity> mTemplateList;
    private WorkAndRestTemplateListAdapter mAdapterWorkAndRestList;
    private LinearLayoutManager mLinearLayoutManager;
    private BottomPopUpMenuDialog mListOnLongClickDialog;
    private ArrayList<WorkAndRestTemplateEntity> mDelTemplateList;
    private boolean mIsDelModel;

    public ArrayList<WorkAndRestTemplateEntity> getTemplateList(){
        return mTemplateList;
    }

    @Override
    public int initView() {
        return R.layout.fragment_work_and_rest;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mIsDelModel = false;
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvWorkAndRestList = (RecyclerView) view.findViewById(R.id.rv_work_and_rest_list);
        rvWorkAndRestList.setHasFixedSize(true);
        rvWorkAndRestList.setLayoutManager(mLinearLayoutManager);
        rvWorkAndRestList.setItemAnimator(new DefaultItemAnimator());

        mTemplateList = WorkAndRestTemplateBLL.getTemplateList();//获取模版列表
        mAdapterWorkAndRestList = new WorkAndRestTemplateListAdapter(getContext(), mTemplateList, this, this);

        rvWorkAndRestList.setAdapter(mAdapterWorkAndRestList);
        rvWorkAndRestList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mIsDelModel) {

                } else {
                    int lastItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

                    if (lastItemPosition >= mTemplateList.size() - 1) {
                        ((MainActivity) getActivity()).getMainBinding().fbtnMain.setVisibility(View.GONE);
                    } else {
                        ((MainActivity) getActivity()).getMainBinding().fbtnMain.setVisibility(View.VISIBLE);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }


    @Override
    public void OnButtonClick(int btnType, final int position,int viewTag) {
        switch (btnType) {
            case BottomPopUpMenuDialog.DEL_BUTTON:
                MyMaterialDialog.createMyMaterialDialog(getContext())
                        .title(R.string.dialog_del_title)
                        .content(R.string.war_template_del_dialog_content)
                        .positiveText(R.string.action_sure)
                        .negativeText(R.string.action_cancel)
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (which.name().equals(DialogAction.POSITIVE.name())){
                                    if (WorkAndRestTemplateBLL.delTemplate(mTemplateList.get(position))) {
                                        mAdapterWorkAndRestList.notifyDataSetChanged();
                                        showToast(getString(R.string.dialog_del_success));
                                    }
                                }
                            }
                        })
                        .show();
                break;
            case BottomPopUpMenuDialog.EDIT_BUTTON:
                WARTemplateInfoDialog dialog = new WARTemplateInfoDialog(getContext(), R.style.WARTemplateInfoDialogStyle, mTemplateList.get(position));
                dialog.show();
                break;
            case BottomPopUpMenuDialog.CANCEL_BUTTON:
                mListOnLongClickDialog.dismiss();
                break;
        }
    }

    public void startDelete() {
        mDelTemplateList=new ArrayList<>();
        for (WorkAndRestTemplateEntity entity : mTemplateList) {
            entity.templateIsDelModel.set(true);
        }
        mAdapterWorkAndRestList.notifyDataSetChanged();
        mIsDelModel = true;
    }

    public void quitDelete() {
        for (WorkAndRestTemplateEntity entity : mTemplateList) {
            entity.templateIsDelModel.set(false);
            entity.templateIsDelChecked.set(false);
        }
        mAdapterWorkAndRestList.notifyDataSetChanged();
        mIsDelModel = false;
        mDelTemplateList.clear();
        mDelTemplateList=null;
    }

    public void batchDelete() {
        if (mDelTemplateList==null||mDelTemplateList.size()==0){
            showToast(getString(R.string.war_template_del_null));
            return;
        }
        MyMaterialDialog.createMyMaterialDialog(getContext())
                .title(R.string.dialog_del_title)
                .content(R.string.war_template_del_dialog_content)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which.name().equals(DialogAction.POSITIVE.name())){
                           if (WorkAndRestTemplateBLL.delTemplates(mDelTemplateList)){
                               mAdapterWorkAndRestList.notifyDataSetChanged();
                               showToast(getString(R.string.dialog_del_success));
                           }

                        }
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRecyclerViewItemClick(int position) {
        Intent intent = new Intent(getContext(), WorkAndRestActivity.class);
        intent.putExtra("entity", mTemplateList.get(position).templateID.get());
        startActivity(intent);
    }

    @Override
    public void onRecyclerViewItemLongClick(int position) {
        mListOnLongClickDialog = new BottomPopUpMenuDialog(getContext(), R.style.ListOnLongClickDialogStyle, WorkAndRestFragment.this, position, 0);
        mListOnLongClickDialog.show();
    }

    @Override
    public void onRecyclerViewItemDelModelClick(int position) {
        View view = mLinearLayoutManager.findViewByPosition(position);
        CheckBox cbDel = (CheckBox) view.findViewById(R.id.cb_template_item_del);
        cbDel.toggle();
        WorkAndRestTemplateEntity entity=mTemplateList.get(position);
        if (cbDel.isChecked()) {
            entity.templateIsDelChecked.set(true);
            mDelTemplateList.add(entity);
        } else {
            entity.templateIsDelChecked.set(false);
            if (mDelTemplateList.contains(entity)){
                mDelTemplateList.remove(entity);
            }
        }
    }

    @Override
    public void onRecyclerViewItemDelModelLongClick(int position) {

    }
}
