package com.sxy.uclock.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sxy.uclock.MainActivity;
import com.sxy.uclock.R;
import com.sxy.uclock.WorkAndRestActivity;
import com.sxy.uclock.adapter.WorkAndRestListAdapter;
import com.sxy.uclock.base.BaseFragment;
import com.sxy.uclock.model.WorkAndRestTemplateBLL;
import com.sxy.uclock.model.WorkAndRestTemplateEntity;
import com.sxy.uclock.view.DividerItemDecoration;
import com.sxy.uclock.view.ListOnLongClickDialog;
import com.sxy.uclock.view.ListOnLongClickDialog.OnDialogButtonClick;
import com.sxy.uclock.view.WARTemplateInfoDialog;

import java.util.ArrayList;


public class WorkAndRestFragment extends BaseFragment implements OnDialogButtonClick, WorkAndRestListAdapter.WARListAdapterListener {
    private RecyclerView rvWorkAndRestList;
    public ArrayList<WorkAndRestTemplateEntity> mTemplateList;
    public WorkAndRestListAdapter adapterWorkAndRestList;
    private LinearLayoutManager mLinearLayoutManager;
    private ListOnLongClickDialog mListOnLongClickDialog;

    @Override
    public int initView() {
        return R.layout.fragment_work_and_rest;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvWorkAndRestList = (RecyclerView) view.findViewById(R.id.rv_work_and_rest_list);
        rvWorkAndRestList.setHasFixedSize(true);
        rvWorkAndRestList.setLayoutManager(mLinearLayoutManager);
        rvWorkAndRestList.setItemAnimator(new DefaultItemAnimator());
        rvWorkAndRestList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        mTemplateList = WorkAndRestTemplateBLL.getTemplateList();//获取模版列表
        adapterWorkAndRestList = new WorkAndRestListAdapter(mTemplateList, getContext(), this);

        rvWorkAndRestList.setAdapter(adapterWorkAndRestList);
        rvWorkAndRestList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

                if (lastItemPosition >= mTemplateList.size() - 1) {
                    ((MainActivity) getActivity()).mBinding.fbtnMain.setVisibility(View.GONE);
                } else {
                    ((MainActivity) getActivity()).mBinding.fbtnMain.setVisibility(View.VISIBLE);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }


    @Override
    public void OnButtonClick(int btnType, int position) {
        switch (btnType) {
            case ListOnLongClickDialog.DEL_BUTTON:
                if (WorkAndRestTemplateBLL.delTemplate(mTemplateList.get(position))) {
                    mTemplateList.remove(position);
                    adapterWorkAndRestList.notifyDataSetChanged();
                    showToast("删除成功");
                }
                break;
            case ListOnLongClickDialog.EDIT_BUTTON:
                WARTemplateInfoDialog dialog=new WARTemplateInfoDialog(getContext(),R.style.WARTemplateInfoDialogStyle,mTemplateList.get(position));
                dialog.show();
                break;
            case ListOnLongClickDialog.CANCEL_BUTTON:
                mListOnLongClickDialog.dismiss();
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getContext(), WorkAndRestActivity.class);
        intent.putExtra("lab", "details");
        startActivity(intent);
    }

    @Override
    public void OnLongClick(int position) {
        mListOnLongClickDialog = new ListOnLongClickDialog(getContext(), R.style.ListOnLongClickDialogStyle, WorkAndRestFragment.this, position, 0);
        mListOnLongClickDialog.show();
    }
}
