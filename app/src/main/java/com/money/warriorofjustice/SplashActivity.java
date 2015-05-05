package com.money.warriorofjustice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.money.warriorofjustice.controller.AppController;
import com.money.warriorofjustice.controller.BaseTask;
import com.money.warriorofjustice.controller.ProcessMessagesResponse;

import com.money.warriorofjustice.controller.tasks.ProcessMessagesTask;
import com.money.warriorofjustice.controller.tasks.SyncBlackListKeywordsTask;
import com.money.warriorofjustice.controller.tasks.SyncBlackListSendersTask;
import com.money.warriorofjustice.model.Message;
import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.SyncBlackListKeywordsResponse;
import com.money.warriorofjustice.network.responses.SyncBlackListSendersResponse;
import com.money.warriorofjustice.util.Utils;


public class SplashActivity extends Activity {

    private ProgressDialog loadingDialog;
    private BaseTask task;
    private AppController app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = AppController.getInstance(this);

        task = new SyncBlackListKeywordsTask(this,
                new BaseTask.TaskListener<SyncBlackListKeywordsResponse>() {


                    @Override
                    public void onSuccess(SyncBlackListKeywordsResponse response) {
                        loadingDialog.dismiss();
                        app.saveKeywords(response.getKeywords());


                        task = new SyncBlackListSendersTask(SplashActivity.this,
                                new BaseTask.TaskListener<SyncBlackListSendersResponse>() {


                                    @Override
                                    public void onSuccess(SyncBlackListSendersResponse response) {
                                        loadingDialog.dismiss();
                                        app.saveSenders(response.getSenders());
                                        task = new ProcessMessagesTask(SplashActivity.this,
                                                new BaseTask.TaskListener<ProcessMessagesResponse>() {
                                                    @Override
                                                    public void onSuccess(ProcessMessagesResponse response) {
                                                        loadingDialog.dismiss();
                                                        for (Message message : response.getMessages()) {
                                                            switch(message.getProcessCode()){
                                                                case Message.MSG_PROCESS_CODE_SPAM:
                                                                    app.receivedSpamMessage(message);
                                                                    app.setMessageRead(SplashActivity
                                                                            .this,message.getId());
                                                                break;
                                                                case Message.MSG_PROCESS_CODE_SPAM_POTENTIAL:
                                                                    app.reportPotentialSpamMessage(message);
                                                                break;

                                                            }



                                                            Log.d("MSG", "MSG " + message.getId() + " " + message.getContent() + " " + message.getSender() + " " +
                                                                    " " + message.getTime());
                                                        }
                                                    }

                                                    @Override
                                                    public void onError(int code) {
                                                        loadingDialog.dismiss();


                                                    }
                                                });
                                        loadingDialog = Utils.showLoadingMessage(SplashActivity.this,
                                                R.string.scanning_sms);

                                        ((ProcessMessagesTask)task).execute();
                                    }

                                    @Override
                                    public void onError(int code) {
                                        loadingDialog.dismiss();

                                    }
                                });
                        loadingDialog = Utils.showLoadingMessage(SplashActivity.this,
                                R.string.loading_blacklist_senders);
                        ((SyncBlackListSendersTask)task).execute(new SyncBlackListRequest());

                    }

                    @Override
                    public void onError(int code) {
                        loadingDialog.dismiss();


                    }
                });

        loadingDialog = Utils.showLoadingMessage(SplashActivity.this, R.string.loading_keywords);

        ((SyncBlackListKeywordsTask)task).execute(new SyncBlackListRequest());




    }


}
