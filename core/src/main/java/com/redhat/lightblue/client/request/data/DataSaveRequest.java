package com.redhat.lightblue.client.request.data;

import java.util.Collection;

import com.redhat.lightblue.client.projection.Projection;
import com.redhat.lightblue.client.request.AbstractLightblueDataRequest;
import com.redhat.lightblue.client.util.JSON;

public class DataSaveRequest extends AbstractLightblueDataRequest {

    private Projection[] projections;
    private Object[] objects;
    private Boolean upsert;

	public DataSaveRequest() {

	}

	public DataSaveRequest(String entityName, String entityVersion) {
		this.setEntityName(entityName);
		this.setEntityVersion(entityVersion);
	}

    public void returns(Projection... projection){
        this.projections = projection;
    }

    public void returns(Collection<Projection> projections) {
        this.projections = projections.toArray( new Projection[ projections.size() ] );
    }

    public void create(Object... objects){
        this.objects = objects;
    }

    public void create(Collection<Object> objects){
        this.objects = objects.toArray(new Object[objects.size()]);
    }

    public Boolean isUpsert() {
        return upsert;
    }

    public void setUpsert(Boolean upsert) {
        this.upsert = upsert;
    }

    @Override
    public String getOperationPathParam() {
        return PATH_PARAM_SAVE;
    }

    @Override
    public String getBody() {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"data\":[");
        sb.append(JSON.toJson(objects[0]));
        for (int i = 1; i < objects.length; i++) {
            sb.append(",").append(JSON.toJson(objects[i]));
        }
        sb.append("],\"projection\":[");
        sb.append(projections[0].toJson());

        for (int i = 1; i < projections.length; i++) {
            sb.append(",").append(projections[i].toJson());
        }

        sb.append("]");
        if (upsert!=null) {
            sb.append(",\"upsert\":");
            sb.append(upsert);
        }

        sb.append("}");

        return sb.toString();
    }

}
