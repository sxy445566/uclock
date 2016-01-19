package com.sxy.uclock.view;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sxy.uclock.R;

/**
 * Created by Administrator on 2016/1/16.
 */
public class MyMaterialDialog {
    public static MaterialDialog.Builder createMyMaterialDialog(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.backgroundColorRes(R.color.window_background)
                .contentColorRes(R.color.secondary_text)
                .titleColorRes(R.color.primary_text)
                .negativeColorRes(R.color.primary_text);
        return builder;
    }
}
