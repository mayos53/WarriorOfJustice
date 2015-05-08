package com.money.warriorofjustice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.money.warriorofjustice.controller.AppController;
import com.money.warriorofjustice.util.Utils;


public class SplashActivity extends Activity {

    private ProgressDialog loadingDialog;
    private AppController app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = AppController.getInstance(this);
        loadingDialog = Utils.showLoadingMessage(SplashActivity.this, R.string.synchronizing_blacklists);

        app.syncBlackList(new OperationListener() {
            @Override
            public void onSuccess() {
                loadingDialog.dismiss();
                app.processMessages(null, 0, new OperationListener() {
                    @Override
                    public void onSuccess() {
                        loadingDialog.dismiss();

                    }

                    @Override
                    public void onError(int code) {
                        loadingDialog.dismiss();

                    }
                });
            }

            @Override
            public void onError(int code) {
                loadingDialog.dismiss();

            }
        });

    }


}
