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
import com.sxy.uclock.databinding.ItemWorkAndRestListBinding;
import com.sxy.uclock.model.WorkAndRestTemplateEntity;

import java.util.List;

/**
 * Created by xf on 2015/8/20.
 */
public class WorkAndRestListAdapter extends RecyclerView.Adapter<WorkAndRestListAdapter.MyViewHolder> {
    private List<WorkAndRestTemplateEntity> mList;
    private Context mContext;
    private WARListAdapterListener mWARListAdapterListener;

    public WorkAndRestListAdapter(List<WorkAndRestTemplateEntity> pList, Context pContext,WARListAdapterListener warListAdapterListener) {
        mList = pList;
        mContext = pContext;
        mWARListAdapterListener=warListAdapterListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWorkAndRestListBinding listBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_work_and_rest_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listBinding.getRoot(),mWARListAdapterListener);
        viewHolder.setBinding(listBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final WorkAndRestTemplateEntity entityWorkAndRestTemplate = mList.get(position);
        holder.getBinding().setVariable(BR.templateEntity, entityWorkAndRestTemplate);
        if (entityWorkAndRestTemplate.templateIsUsing.get()) {
            holder.getBinding().ivItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
        } else {
            holder.getBinding().ivItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
        }
        holder.getBinding().layItemIsusing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entityWorkAndRestTemplate.templateIsUsing.get()) {
                    entityWorkAndRestTemplate.templateIsUsing.set(false);
                    holder.getBinding().ivItemIsusing.setColorFilter(android.R.color.black, PorterDuff.Mode.DST_ATOP);
                } else {
                    entityWorkAndRestTemplate.templateIsUsing.set(true);
                    holder.getBinding().ivItemIsusing.setColorFilter(R.color.primary, PorterDuff.Mode.DST_ATOP);
                }
            }
        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private ItemWorkAndRestListBinding binding;
        private WARListAdapterListener mWARListAdapterListener;

        public MyViewHolder(View itemView, WARListAdapterListener warListAdapterListener) {
            super(itemView);
            mWARListAdapterListener=warListAdapterListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public ItemWorkAndRestListBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemWorkAndRestListBinding binding) {
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            mWARListAdapterListener.onClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            mWARListAdapterListener.OnLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface WARListAdapterListener{
        void onClick(int position);
        void OnLongClick(int position);
    }
}
