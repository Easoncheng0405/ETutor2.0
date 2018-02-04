package com.example.etutor.util;

import android.app.Activity;
import android.view.View;

import com.example.etutor.InitApplication;
import com.example.etutor.dialog.SureDialog;


/**
 * Created by 医我一生 on 2018/2/2.
 * Email  597021782@qq.com
 * Github https://github.com/Easoncheng0405
 * 通过构造不同的UpdateUITools对象来实现不同的UI更新操作
 */

public class UpdateUITools implements Runnable {

    private static final int DialogMessage = 0;

    private static final int ToastMessage = 1;

    public static final int ForceClose = 0;

    public static final int DoNothing = 1;
    /**
     *
     */
    private int option;
    private SureDialog sureDialog;
    private String title, message;
    private Activity activity;
    private int action;

    public UpdateUITools(Activity activity, String title, String message, int action) {
        this.activity = activity;
        this.title = title;
        this.message = message;
        this.action = action;
        this.option = DialogMessage;
    }

    public UpdateUITools(String message) {
        this.message = message;
        this.option = ToastMessage;
    }

    @Override
    public void run() {
        switch (option) {
            case DialogMessage:
                initSureDialog();
                break;
            case ToastMessage:
                ToastUtil.showMessage(InitApplication.getContext(), message);
                break;
            default:
                break;
        }
    }

    public void initSureDialog() {
        sureDialog = new SureDialog(activity);
        sureDialog.setCancelable(false);
        sureDialog.getTvContent().setText(message);
        sureDialog.getTvContent().setGravity(android.view.Gravity.CENTER);
        sureDialog.getTvTitle().setText(title);
        sureDialog.getTvSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (action) {
                    case ForceClose:
                        activity.finish();
                        break;
                    case DoNothing:
                        sureDialog.dismiss();
                        break;
                    default:
                        break;

                }
            }
        });
        sureDialog.show();
    }


}
