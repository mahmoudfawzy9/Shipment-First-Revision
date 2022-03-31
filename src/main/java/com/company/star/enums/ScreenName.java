package com.company.star.enums;

public enum ScreenName {
	DELIVERY_REQUEST_FEDEX("deliveryRequestFedex"),DELIVERY_REQUEST_UPS("deliveryRequestUPS"), CHAT("chat");

	private final String value;

	ScreenName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ScreenName fromValue(String value) {
		ScreenName[] allValues = ScreenName.values();

		for (ScreenName sn : allValues) {
			if (value.equals(sn.getValue())) {
				return sn;
			}
		}
		return null;
	}
}
