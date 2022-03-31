package com.company.star.utils;

public class Constants {

	public static final String MESSAGE_INVALID_DATA = "Invalid Data";
	private static final String PROJECT_ID = "inlaid-stratum-253214";
	// public static final String SERVER_KEY = "
	// AAAAGGBsc4o:APA91bHCPSB46asPa69ZmuxzqJLHB61tKT5fV1btiINPD4XbVISweTWrni9XwV8RmPBy4VpmT-66S9XUiUS3BTjqXr2pcj4L91sVaYPZ_cHsDv8ZQ7_PbmjPPKpTLzp_IqaqTPgUWy0Z";
	public static final String FCM_URL = String.format("https://fcm.googleapis.com/v1/projects/%s/messages:send",
			PROJECT_ID);
//	 public static final String FCM_URL_LEGACY =
//	 "https://fcm.googleapis.com/fcm/send";
	public static final String CONTENT_TYPE = "application/json; UTF-8";

	public static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/cloud-platform";
	public static final String[] SCOPES = { MESSAGING_SCOPE };
	public static final boolean FCM_TEST = false;

	public static final int SHIPMENT_TYPE_FEDEX = 1;
	public static final int SHIPMENT_TYPE_UPS = 3;

}
