package com.money.warriorofjustice.controller.tasks;

import android.content.Context;

import com.money.warriorofjustice.controller.BaseTask;
import com.money.warriorofjustice.network.NetworkClient;
import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.SyncBlackListSendersResponse;

/**
 * Created by Yossef on 4/26/15.
 */

public class SyncBlackListSendersTask extends BaseTask<SyncBlackListRequest,Integer,
        SyncBlackListSendersResponse> {


    public SyncBlackListSendersTask(Context context, TaskListener<SyncBlackListSendersResponse> listener) {
        super(context, listener);
    }



    @Override
    protected SyncBlackListSendersResponse doInBackground(SyncBlackListRequest... syncBlackListRequests) {
        NetworkClient client = new NetworkClient();
        return client.syncBlackListSenders(syncBlackListRequests[0]);
    }


}


