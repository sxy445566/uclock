package com.sxy.uclock.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sxy.uclock.R;
import com.sxy.uclock.databinding.DialogListOnLongClickBinding;

/**
 * Created by 123 on 2015/9/11.
 */
public class ListOnLongClickDialog extends Dialog implements View.OnClickListener {
    public static final int DEL_BUTTON = 0;
    public static final int CANCEL_BUTTON = 1;
    private DialogListOnLongClickBinding mBinding;
    private Context mContext;
    private OnDialogButtonClick mOnDialogButtonClick;

    public ListOnLongClickDialog(Context context, int themeResId, OnDialogButtonClick onDialogButtonClick) {
        super(context, themeResId);
        mContext = context;
        mOnDialogButtonClick = onDialogButtonClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_list_on_long_click, null, false);
        setContentView(mBinding.getRoot());
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBinding.btnLongClickDialogDelete.setOnClickListener(this);
        mBinding.btnLongClickDialogCancel.setOnClickListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.dismiss();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_long_click_dialog_delete:
                mOnDialogButtonClick.OnButtonClick(DEL_BUTTON);
                break;
            case R.id.btn_long_click_dialog_cancel:
                mOnDialogButtonClick.OnButtonClick(CANCEL_BUTTON);
                break;
        }
        this.dismiss();
    }

    public interface OnDialogButtonClick {
        void OnButtonClick(int btnType);
    }
}
