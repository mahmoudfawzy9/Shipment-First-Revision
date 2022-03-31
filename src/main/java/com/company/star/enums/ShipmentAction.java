package com.company.star.enums;

public enum ShipmentAction {
	NAVIGATE_TO_SCREEN(1), CALL_NUMBER(2), OPEN_LINK(3);

	private final int value;

	ShipmentAction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ShipmentAction fromValue(int value) {
		ShipmentAction[] allValues = ShipmentAction.values();

		for (ShipmentAction na : allValues) {
			if (value == na.getValue()) {
				return na;
			}
		}
		return null;
	}
}
