package com.money.warriorofjustice.controller.tasks;

import android.content.Context;

import com.money.warriorofjustice.network.requests.SyncBlackListRequest;
import com.money.warriorofjustice.network.responses.SyncBlackListResponse;

/**
 * Created by Yossef on 4/26/15.
 */

public class SyncBlackListTask extends BaseTask<SyncBlackListRequest,Integer,
        SyncBlackListResponse> {


    public SyncBlackListTask(Context context, TaskListener<SyncBlackListResponse> listener) {
        super(context, listener);
    }



    @Override
    protected SyncBlackListResponse doInBackground(SyncBlackListRequest... syncBlackListRequests) {
        return networkClient.syncBlackLists(syncBlackListRequests[0]);

    }


}


