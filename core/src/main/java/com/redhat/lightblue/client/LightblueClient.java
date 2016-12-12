package com.redhat.lightblue.client;

import com.redhat.lightblue.client.request.DataBulkRequest;
import com.redhat.lightblue.client.request.LightblueDataRequest;
import com.redhat.lightblue.client.request.LightblueMetadataRequest;
import com.redhat.lightblue.client.response.LightblueBulkDataResponse;
import com.redhat.lightblue.client.response.LightblueDataResponse;
import com.redhat.lightblue.client.response.LightblueMetadataResponse;

public interface LightblueClient {

    Locking getLocking(String domain);

    /**
     * This method allows
     * @param domain
     * @param usePost
     * @return
     */
    Locking getLocking(String domain, Boolean usePost);

    LightblueMetadataResponse metadata(LightblueMetadataRequest lightblueRequest) throws LightblueException;

    LightblueDataResponse data(LightblueDataRequest lightblueRequest) throws LightblueException;

    LightblueBulkDataResponse bulkData(DataBulkRequest requests) throws LightblueException;

    <T> T data(LightblueDataRequest lightblueRequest, Class<T> type) throws LightblueException;

}
