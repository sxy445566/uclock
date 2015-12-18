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
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    public WorkAndRestListAdapter(List<WorkAndRestTemplateEntity> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWorkAndRestListBinding listBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_work_and_rest_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listBinding.getRoot(), mOnClickListener, mOnLongClickListener);
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

    public void setOnClickListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        mOnLongClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkAndRestListBinding binding;

        public MyViewHolder(View itemView, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
            super(itemView);
            itemView.setOnClickListener(onClickListener);
            itemView.setOnLongClickListener(onLongClickListener);
        }

        public ItemWorkAndRestListBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemWorkAndRestListBinding binding) {
            this.binding = binding;
        }

    }
}
