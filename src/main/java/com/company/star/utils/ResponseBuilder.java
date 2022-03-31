package com.company.star.utils;

import org.json.JSONObject;

public class ResponseBuilder {
	public static final String TAG_RESPONSE_CODE = "responseCode";
	public static final String TAG_MESSAGE = "message";
	public static final String TAG_DATA = "data";
	public static final String TAG_ERROR = "error";

	private ResponseBuilder() {

	}

	public static JSONObject success() {
		return success(null);
	}

	public static JSONObject success(String message) {
		JSONObject obj = new JSONObject();

		obj.put(TAG_RESPONSE_CODE, 1);

		if (message != null)
			obj.put(TAG_MESSAGE, message);

		return obj;
	}

	public static JSONObject failure() {
		return failure(null);
	}

	public static JSONObject failure(String message) {
		JSONObject obj = new JSONObject();

		obj.put(TAG_RESPONSE_CODE, 0);

		if (message != null)
			obj.put(TAG_MESSAGE, message);

		return obj;
	}

	public static JSONObject failure(String message, String error) {
		JSONObject obj = new JSONObject();

		obj.put(TAG_RESPONSE_CODE, 0);
		obj.put(TAG_ERROR, error);

		if (message != null)
			obj.put(TAG_MESSAGE, message);

		return obj;
	}
}
