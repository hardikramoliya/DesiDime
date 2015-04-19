package com.android.http;

public interface HttpCallback {

    /**
     * Callback method for http response. called when an http resounce is received.
     *
     * @param response
     * @param action
     */
    public void onResponse(String response, int action);

}