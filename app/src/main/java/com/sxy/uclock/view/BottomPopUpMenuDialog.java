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
import com.sxy.uclock.databinding.DialogBottomPopUpMenuBinding;

/**
 * Created by 123 on 2015/9/11.
 */
public class BottomPopUpMenuDialog extends Dialog implements View.OnClickListener {
    public static final int CANCEL_BUTTON = 0;
    public static final int DEL_BUTTON = -1;
    public static final int EDIT_BUTTON = 1;
    public static final int TAG_LONG_CLICK = 0;
    public static final int TAG_MENU_CLICK = 1;
    private int mViewTag;
    private DialogBottomPopUpMenuBinding mBinding;
    private Context mContext;
    private OnDialogButtonClick mOnDialogButtonClick;
    private int mPosition;

    public BottomPopUpMenuDialog(Context context, int themeResId, OnDialogButtonClick onDialogButtonClick, int position, int viewTag) {
        super(context, themeResId);
        mContext = context;
        mOnDialogButtonClick = onDialogButtonClick;
        mPosition = position;
        mViewTag = viewTag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_bottom_pop_up_menu, null, false);
        setContentView(mBinding.getRoot());
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (mViewTag == TAG_LONG_CLICK) {
            mBinding.layLongClickDialogEdit.setVisibility(View.VISIBLE);
            mBinding.btnLongClickDialogEdit.setText(R.string.action_edit);
            mBinding.btnLongClickDialogDelete.setText(R.string.action_delete);
        } else if (mViewTag == TAG_MENU_CLICK) {
            mBinding.layLongClickDialogEdit.setVisibility(View.VISIBLE);
            mBinding.btnLongClickDialogEdit.setText(R.string.action_add);
            mBinding.btnLongClickDialogDelete.setText(R.string.action_batch_delete);
        }
        mBinding.btnLongClickDialogDelete.setOnClickListener(this);
        mBinding.btnLongClickDialogEdit.setOnClickListener(this);
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
                mOnDialogButtonClick.OnButtonClick(DEL_BUTTON, mPosition,mViewTag);
                break;
            case R.id.btn_long_click_dialog_cancel:
                mOnDialogButtonClick.OnButtonClick(CANCEL_BUTTON, mPosition,mViewTag);
                break;
            case R.id.btn_long_click_dialog_edit:
                mOnDialogButtonClick.OnButtonClick(EDIT_BUTTON, mPosition,mViewTag);
                break;
        }
        this.dismiss();
    }

    public interface OnDialogButtonClick {
        void OnButtonClick(int btnType, int position,int viewTag);
    }
}
