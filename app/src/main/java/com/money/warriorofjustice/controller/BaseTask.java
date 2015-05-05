package com.money.warriorofjustice.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.money.warriorofjustice.network.requests.BaseRequest;
import com.money.warriorofjustice.network.responses.BaseResponse;

/**
 * Created by Yossef on 4/26/15.
 */
public abstract class BaseTask<T extends BaseRequest,U, V extends BaseResponse> extends
        AsyncTask<T,U,V> {

   protected Context context;
   protected TaskListener<V> listener;


    public BaseTask(Context context,TaskListener<V> listener){
       this.context = context;
       this.listener = listener;
   }



    @Override
    protected void onPostExecute(V baseResponse) {
        super.onPostExecute(baseResponse);
        if(listener != null) {
            if (baseResponse.getStatusCode() == BaseResponse.STATUS_CODE_OK) {
                listener.onSuccess(baseResponse);
            }else{
                listener.onError(baseResponse.getStatusCode());
            }
        }
    }

    public static interface TaskListener<V extends BaseResponse> {
        public void onSuccess(V response);
        public void onError(int code);

    }
}
