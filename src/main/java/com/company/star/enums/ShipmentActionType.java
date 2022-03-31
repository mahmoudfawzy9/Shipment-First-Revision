package com.company.star.enums;

public enum ShipmentActionType {
	SHIPTOHOME("shiptohome"), SHIPTOTHEOFFICE("shiptotheoffice"), LINK("link");

	private final String value;

	ShipmentActionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public ShipmentActionType fromValue(String value) {
		ShipmentActionType[] allValues = ShipmentActionType.values();

		for (ShipmentActionType nat : allValues) {
			if (value.equals(nat.getValue())) {
				return nat;
			}
		}
		return null;
	}
}
