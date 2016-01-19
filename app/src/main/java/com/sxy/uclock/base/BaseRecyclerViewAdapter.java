package com.sxy.uclock.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/1/15.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;

    public BaseRecyclerViewAdapter(Context pContext) {
        mContext = pContext;
    }

    public interface RecyclerViewAdapterListener {
        void onRecyclerViewItemClick(int position);

        void onRecyclerViewItemLongClick(int position);
    }

    public interface RecyclerViewAdapterDelModelListener {
        void onRecyclerViewItemDelModelClick(int position);

        void onRecyclerViewItemDelModelLongClick(int position);
    }
}
