/**
 * 
 */
package com.redhat.lightblue.client.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.lightblue.client.request.data.DataDeleteRequest;
import com.redhat.lightblue.client.request.data.DataFindRequest;
import com.redhat.lightblue.client.request.data.DataInsertRequest;
import com.redhat.lightblue.client.request.data.DataSaveRequest;
import com.redhat.lightblue.client.request.data.DataUpdateRequest;
import com.redhat.lightblue.client.util.JSON;

/**
 * @author bvulaj
 *
 */
public class BulkLightblueDataRequest extends AbstractBulkLightblueRequest<AbstractLightblueDataRequest> {

	@Override
	public JsonNode getBodyJson() {
		ArrayNode node = JsonNodeFactory.instance.arrayNode();
		for (AbstractLightblueDataRequest req : reqs) {
			ObjectNode seqNode = JsonNodeFactory.instance.objectNode();
			seqNode.set("seq", JSON.toJsonNode(node.size()));
			String op = "";
			if (req instanceof DataDeleteRequest) {
				op = "delete";
			} else if (req instanceof DataFindRequest) {
				op = "find";
			} else if (req instanceof DataInsertRequest) {
				op = "insert";
			} else if (req instanceof DataSaveRequest) {
				op = "save";
			} else if (req instanceof DataUpdateRequest) {
				op = "update";
			}
			seqNode.set("op", JSON.toJsonNode(op));
			seqNode.set("request", req.getBodyJson());
			node.add(seqNode);
		}
		return node;
	}
}
