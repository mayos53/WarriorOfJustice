package com.money.warriorofjustice.controller.tasks;

import android.content.Context;

import com.money.warriorofjustice.controller.BaseTask;
import com.money.warriorofjustice.network.NetworkClient;
import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.SyncBlackListKeywordsResponse;

/**
 * Created by Yossef on 4/26/15.
 */

public class SyncBlackListKeywordsTask extends BaseTask<SyncBlackListRequest,Integer,
        SyncBlackListKeywordsResponse> {


    public SyncBlackListKeywordsTask(Context context, TaskListener<SyncBlackListKeywordsResponse> listener) {
        super(context, listener);
    }



    @Override
    protected SyncBlackListKeywordsResponse doInBackground(SyncBlackListRequest... syncBlackListRequests) {
        NetworkClient client = new NetworkClient();
        return client.syncBlackListKeywords(syncBlackListRequests[0]);

    }


}


