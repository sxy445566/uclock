package com.sxy.uclock.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.sxy.uclock.R;
import com.sxy.uclock.databinding.DialogBatchDeleteBinding;

/**
 * Created by 123 on 2015/9/11.
 */
public class BatchDeleteDialog extends Dialog implements View.OnClickListener {
    private DialogBatchDeleteBinding mBinding;
    private Context mContext;
    private OnBatchDeleteDialogButtonClick mOnBatchDeleteDialogButtonClick;

    public BatchDeleteDialog(Context context, int themeResId, OnBatchDeleteDialogButtonClick onBatchDeleteDialogButtonClick) {
        super(context, themeResId);
        mContext = context;
        mOnBatchDeleteDialogButtonClick = onBatchDeleteDialogButtonClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_batch_delete, null, false);
        setContentView(mBinding.getRoot());
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBinding.btnBatchDeleteDialogCancel.setOnClickListener(this);
        mBinding.btnBatchDeleteDialogDelete.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.dismiss();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_batch_delete_dialog_cancel:
                this.dismiss();
                break;
            case R.id.btn_batch_delete_dialog_delete:
                mOnBatchDeleteDialogButtonClick.OnButtonClick();
                break;
        }
        this.dismiss();
    }

    public interface OnBatchDeleteDialogButtonClick {
        void OnButtonClick();
    }
}
