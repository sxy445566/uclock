package com.sxy.uclock.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxy.uclock.BR;
import com.sxy.uclock.R;
import com.sxy.uclock.base.BaseRecyclerViewAdapter;
import com.sxy.uclock.base.BaseRecyclerViewHolder;
import com.sxy.uclock.databinding.ItemWorkAndRestListBinding;
import com.sxy.uclock.model.WorkAndRestDetailsEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/19.
 */
public class WorkAndRestListAdapter extends BaseRecyclerViewAdapter {
    private ArrayList<WorkAndRestDetailsEntity> mList;
    private static RecyclerViewAdapterListener mWARListAdapterListener;
    private static RecyclerViewAdapterDelModelListener mWARListAdapterDelModelListener;

    public WorkAndRestListAdapter(Context pContext, ArrayList<WorkAndRestDetailsEntity> pList, RecyclerViewAdapterListener warListAdapterListener, RecyclerViewAdapterDelModelListener warListAdapterDelModelListener) {
        super(pContext);
        mList = pList;
        mWARListAdapterListener=warListAdapterListener;
        mWARListAdapterDelModelListener=warListAdapterDelModelListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWorkAndRestListBinding binding= DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_work_and_rest_list,parent,false);
        MyViewHolder holder=new MyViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hold, int position) {
        MyViewHolder holder= (MyViewHolder) hold;
        final ItemWorkAndRestListBinding binding= (ItemWorkAndRestListBinding) holder.getBinding();
        final WorkAndRestDetailsEntity entity=mList.get(position);
        binding.setVariable(BR.detailsEntity,entity);
        if (entity.detailsIsDelModel.get()) {
            binding.ivDetailsItemIsusing.setVisibility(View.GONE);
            binding.cbDetailsItemDel.setVisibility(View.VISIBLE);
            if (entity.detailsIsDelChecked.get()){
                binding.cbDetailsItemDel.setChecked(true);
            }else {
                binding.cbDetailsItemDel.setChecked(false);
            }
            binding.cbDetailsItemDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.cbDetailsItemDel.isChecked()){
                        entity.detailsIsDelChecked.set(true);
                    }else{
                        entity.detailsIsDelChecked.set(false);
                    }
                }
            });
        } else {
            binding.ivDetailsItemIsusing.setVisibility(View.VISIBLE);
            binding.cbDetailsItemDel.setVisibility(View.GONE);
            if (entity.detailsIsUsing.get()) {
                binding.ivDetailsItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
            } else {
                binding.ivDetailsItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
            }
            binding.layDetailsItemIsusing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (entity.detailsIsUsing.get()){
                       entity.detailsIsUsing.set(false);
                       binding.ivDetailsItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
                   }else{
                       entity.detailsIsUsing.set(true);
                       binding.ivDetailsItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
                   }
                }
            });
        }
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyViewHolder extends BaseRecyclerViewHolder {

        private MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            if (mList.get(getAdapterPosition()).detailsIsDelModel.get()) {
                mWARListAdapterDelModelListener.onRecyclerViewItemDelModelClick(getAdapterPosition());
            } else {
                mWARListAdapterListener.onRecyclerViewItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mList.get(getAdapterPosition()).detailsIsDelModel.get()) {
                mWARListAdapterDelModelListener.onRecyclerViewItemDelModelLongClick(getAdapterPosition());
            } else {
                mWARListAdapterListener.onRecyclerViewItemLongClick(getAdapterPosition());
            }
            return true;
        }
    }
}
