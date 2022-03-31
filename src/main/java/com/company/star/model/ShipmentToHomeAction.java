package com.company.star.model;

import com.company.star.enums.ShipmentActionType;

public class ShipmentToHomeAction extends ShipmentAction {
	private String linkUrl;

	public ShipmentToHomeAction(String linkUrl) {
		super();
		super.setShipmentType(ShipmentActionType.SHIPTOHOME.getValue());
		this.linkUrl = linkUrl;
	}

	public String getLinkURL() {
		return linkUrl;
	}

	public void setLinkURL(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
