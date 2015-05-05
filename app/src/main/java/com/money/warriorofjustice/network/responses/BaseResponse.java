package com.money.warriorofjustice.network.responses;




public abstract class BaseResponse {
	
	public final static int STATUS_CODE_OK = 1;

    protected int statusCode = STATUS_CODE_OK;
	protected String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
