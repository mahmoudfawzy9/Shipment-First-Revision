package com.company.star.model;

import com.company.star.enums.ShipmentActionType;

public class ShipmentToOfficeAction extends ShipmentAction {
	private String linkUrl;

	public ShipmentToOfficeAction(String linkUrl) {
		super();
		super.setShipmentType(ShipmentActionType.SHIPTOTHEOFFICE.getValue());
		this.setLinkUrl(linkUrl);
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
