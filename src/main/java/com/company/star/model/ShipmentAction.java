package com.company.star.model;

public class ShipmentAction {
	private String clickAction;
	private String shipmentType;

	public ShipmentAction() {
		this.clickAction = "FLUTTER_NOTIFICATION_CLICK";
	}

	public String getClickAction() {
		return clickAction;
	}

	public void setClickAction(String clickAction) {
		this.clickAction = clickAction;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

}
