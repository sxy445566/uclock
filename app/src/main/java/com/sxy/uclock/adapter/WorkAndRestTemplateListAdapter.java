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
import com.sxy.uclock.databinding.ItemWorkAndRestTemplateListBinding;
import com.sxy.uclock.db.WorkAndRestTemplate;

import java.util.List;

/**
 * Created by sxy on 2015/8/20.
 */
public class WorkAndRestTemplateListAdapter extends BaseRecyclerViewAdapter {
    private static List<WorkAndRestTemplate> mList;
    private static RecyclerViewAdapterListener mWARListAdapterListener;
    private static RecyclerViewAdapterDelModelListener mWARListAdapterDelModelListener;

    public WorkAndRestTemplateListAdapter(Context pContext, List<WorkAndRestTemplate> pList, RecyclerViewAdapterListener warListAdapterListener, RecyclerViewAdapterDelModelListener warListAdapterDelModelListener) {
        super(pContext);
        mList = pList;
        mWARListAdapterListener = warListAdapterListener;
        mWARListAdapterDelModelListener = warListAdapterDelModelListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWorkAndRestTemplateListBinding listBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_work_and_rest_template_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listBinding.getRoot());
        viewHolder.setBinding(listBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder hold, int position) {
        final MyViewHolder holder = (MyViewHolder) hold;
        final ItemWorkAndRestTemplateListBinding binding= (ItemWorkAndRestTemplateListBinding) holder.getBinding();
        final WorkAndRestTemplate entityWorkAndRestTemplate = mList.get(position);
        binding.setVariable(BR.templateEntity, entityWorkAndRestTemplate);
        if (entityWorkAndRestTemplate.isTemplateIsDelModel()) {
            binding.ivTemplateItemIsusing.setVisibility(View.GONE);
            binding.cbTemplateItemDel.setVisibility(View.VISIBLE);
            if (entityWorkAndRestTemplate.isTemplateIsDelChecked()){
                binding.cbTemplateItemDel.setChecked(true);
            }else {
                binding.cbTemplateItemDel.setChecked(false);
            }
            binding.cbTemplateItemDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.cbTemplateItemDel.isChecked()){
                        entityWorkAndRestTemplate.setTemplateIsDelChecked(true);
                    }else{
                        entityWorkAndRestTemplate.setTemplateIsDelChecked(false);
                    }
                }
            });
        } else {
            binding.ivTemplateItemIsusing.setVisibility(View.VISIBLE);
            binding.cbTemplateItemDel.setVisibility(View.GONE);
            if (entityWorkAndRestTemplate.getTemplateIsUsing()) {
                binding.ivTemplateItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
            } else {
                binding.ivTemplateItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
            }
            binding.layTemplateItemIsusing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (entityWorkAndRestTemplate.getTemplateIsUsing()) {
                        entityWorkAndRestTemplate.setTemplateIsUsing(false);
                        binding.ivTemplateItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
                    } else {
                        entityWorkAndRestTemplate.setTemplateIsUsing(true);
                        binding.ivTemplateItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
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

    private class MyViewHolder extends BaseRecyclerViewHolder{

        private MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            if (mList.get(getAdapterPosition()).isTemplateIsDelModel()) {
                mWARListAdapterDelModelListener.onRecyclerViewItemDelModelClick(getAdapterPosition());
            } else {
                mWARListAdapterListener.onRecyclerViewItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mList.get(getAdapterPosition()).isTemplateIsDelModel()) {
                mWARListAdapterDelModelListener.onRecyclerViewItemDelModelLongClick(getAdapterPosition());
            } else {
                mWARListAdapterListener.onRecyclerViewItemLongClick(getAdapterPosition());
            }
            return true;
        }
    }
}
