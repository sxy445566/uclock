package com.sxy.uclock.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sxy.uclock.R;
import com.sxy.uclock.databinding.DialogWartemplateInfoBinding;
import com.sxy.uclock.db.WorkAndRestTemplate;
import com.sxy.uclock.model.WorkAndRestTemplateBLL;

/**
 * 新建或修改作息模版Dialog
 */
public class WARTemplateInfoDialog extends Dialog implements View.OnClickListener {
    private DialogWartemplateInfoBinding mBinding;
    private Context mContext;
    private WorkAndRestTemplate mTemplateEntity;
    private String mOldDays;

    public WARTemplateInfoDialog(Context context, int themeResId, WorkAndRestTemplate entity) {
        super(context, themeResId);
        mContext = context;
        if (entity == null) {
            mTemplateEntity = new WorkAndRestTemplate();
        } else {
            mTemplateEntity = entity;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_wartemplate_info, null, false);
        setContentView(mBinding.getRoot());
        mBinding.setTemplateEntity(mTemplateEntity);
        mOldDays = mTemplateEntity.getTemplateDays();
        mBinding.executePendingBindings();//立即执行绑定数据操作
        if (mTemplateEntity.getTemplateName() != null) {
            mBinding.etWarTemplateInfo.setSelection(mTemplateEntity.getTemplateName().length() > 0 ? (mTemplateEntity.getTemplateName().length()) : 0);
        }
        setListener();
    }

    private void setListener() {
        mBinding.cvWarTemplateInfoMon.setOnClickListener(this);
        mBinding.cvWarTemplateInfoTue.setOnClickListener(this);
        mBinding.cvWarTemplateInfoWed.setOnClickListener(this);
        mBinding.cvWarTemplateInfoThu.setOnClickListener(this);
        mBinding.cvWarTemplateInfoFri.setOnClickListener(this);
        mBinding.cvWarTemplateInfoSat.setOnClickListener(this);
        mBinding.cvWarTemplateInfoSun.setOnClickListener(this);

        mBinding.cvWarTemplateInfoCancel.setOnClickListener(this);
        mBinding.cvWarTemplateInfoSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_war_template_info_mon:
                editDays(1);
                break;
            case R.id.cv_war_template_info_tue:
                editDays(2);
                break;
            case R.id.cv_war_template_info_wed:
                editDays(3);
                break;
            case R.id.cv_war_template_info_thu:
                editDays(4);
                break;
            case R.id.cv_war_template_info_fri:
                editDays(5);
                break;
            case R.id.cv_war_template_info_sat:
                editDays(6);
                break;
            case R.id.cv_war_template_info_sun:
                editDays(7);
                break;
            case R.id.cv_war_template_info_cancel:
                mTemplateEntity.setTemplateDays(mOldDays);
                this.dismiss();
                break;
            case R.id.cv_war_template_info_save:
                String name=mBinding.etWarTemplateInfo.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(mContext, "请输入模板名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isSave;
                mTemplateEntity.setTemplateName(name);
                if (TextUtils.isEmpty(mTemplateEntity.getTemplateDays())){
                    mTemplateEntity.setTemplateDays("");
                }
                if (mTemplateEntity.getTemplateID()==null) {
                    isSave = WorkAndRestTemplateBLL.addWorkAndRestTemplateEntity(mTemplateEntity);
                } else {
                    isSave = WorkAndRestTemplateBLL.modifyWorkAndRestTemplateEntity(mTemplateEntity);
                }
                if (isSave) {
                    Toast.makeText(mContext, "已保存", Toast.LENGTH_SHORT).show();
                    this.dismiss();
                }
                break;
        }
    }

    private void editDays(int day) {
        if (WorkAndRestTemplateBLL.isHaveDay(mTemplateEntity.getTemplateDays(), day)) {
            WorkAndRestTemplateBLL.delDay(mTemplateEntity, day);
        } else {
            WorkAndRestTemplateBLL.addDay(mTemplateEntity, day);
        }
    }
}
