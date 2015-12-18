package com.sxy.uclock.fragment;

import android.content.Intent;
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
import com.sxy.uclock.view.ListOnLongClickDialog;
import com.sxy.uclock.view.ListOnLongClickDialog.OnDialogButtonClick;

import java.util.ArrayList;


public class WorkAndRestFragment extends BaseFragment implements OnDialogButtonClick {
    private RecyclerView rvWorkAndRestList;
    private ArrayList<WorkAndRestTemplateEntity> list;
    private WorkAndRestListAdapter adapterWorkAndRestList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int initView() {
        return R.layout.fragment_work_and_rest;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvWorkAndRestList = (RecyclerView) view.findViewById(R.id.rv_work_and_rest_list);
        rvWorkAndRestList.setHasFixedSize(true);
        rvWorkAndRestList.setLayoutManager(linearLayoutManager);
        rvWorkAndRestList.setItemAnimator(new DefaultItemAnimator());

        list = WorkAndRestTemplateBLL.getTemplateList();//获取模版列表
        adapterWorkAndRestList = new WorkAndRestListAdapter(list, getContext());
        adapterWorkAndRestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WorkAndRestActivity.class);
                intent.putExtra("lab","details");
                startActivity(intent);
            }
        });
        adapterWorkAndRestList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ListOnLongClickDialog dialog=new ListOnLongClickDialog(getActivity(),R.style.ListOnLongClickDialogStyle,WorkAndRestFragment.this);
                dialog.show();
                return true;
            }
        });
        rvWorkAndRestList.setAdapter(adapterWorkAndRestList);
        rvWorkAndRestList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                if (lastItemPosition >= list.size() - 1) {
                    ((MainActivity) getActivity()).mBinding.fbtnMain.setVisibility(View.GONE);
                } else {
                    ((MainActivity) getActivity()).mBinding.fbtnMain.setVisibility(View.VISIBLE);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }


    @Override
    public void OnButtonClick(int btnType) {
        switch (btnType){
            case ListOnLongClickDialog.DEL_BUTTON:

                break;
            case ListOnLongClickDialog.CANCEL_BUTTON:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
