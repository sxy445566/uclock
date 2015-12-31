package com.sxy.uclock.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.sxy.uclock.R;
import com.sxy.uclock.databinding.DialogWartemplateInfoBinding;
import com.sxy.uclock.model.WorkAndRestTemplateEntity;

/**
 * 新建或修改作息模版Dialog
 */
public class WARTemplateInfoDialog extends Dialog {
    private DialogWartemplateInfoBinding mBinding;
    private Context mContext;
    private WorkAndRestTemplateEntity mTemplateEntity;

    public WARTemplateInfoDialog(Context context, int themeResId, WorkAndRestTemplateEntity entity) {
        super(context, themeResId);
        mContext = context;
        mTemplateEntity = entity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_wartemplate_info, null, false);
        setContentView(mBinding.getRoot());
        mBinding.setTemplateEntity(mTemplateEntity);
        mBinding.executePendingBindings();//立即执行绑定数据操作
        if (mTemplateEntity.templateName.get() != null) {
            mBinding.etWarTemplateInfo.setSelection(mTemplateEntity.templateName.get().length() > 0 ? (mTemplateEntity.templateName.get().length()) : 1);
        }
    }
}
