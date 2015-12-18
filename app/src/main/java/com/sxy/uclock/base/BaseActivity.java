package com.sxy.uclock.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sxy.uclock.R;

/**
 * Created by 123 on 2015/9/7.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        showToast(msg, 0);
    }

    protected void showToast(String msg, int time) {
        Toast.makeText(this,msg,time).show();
    }

//    protected CustomProgressDialog dialog;
//
//    /**
//     * 显示圆形进度条
//     *
//     * @param content
//     */
//    protected void showProgressDialog(String content) {
//        if (dialog == null && ct != null) {
//            dialog = (CustomProgressDialog) DialogUtil.createProgressDialog(ct);
//        }
//        dialog.show();
//    }
//
//    /**
//     * 关闭进度条
//     */
//    protected void closeProgressDialog() {
//        if (dialog != null) dialog.dismiss();
//    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }
}
