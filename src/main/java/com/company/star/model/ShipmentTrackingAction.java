package com.company.star.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.company.star.enums.ScreenName;
import com.company.star.enums.ShipmentActionType;

public class ShipmentTrackingAction extends ShipmentAction {
	private static final String CARRIER_ID = "carrierId";
	private static final String SHIPMENT_ID = "shipmentId";
	private static final String CHAT_ID = "chatId";

	private String trackURL;
	private String carrierId;
	private String shipmentId;
	private String chatId;

	private String username;
	private String avatar;
	private String price;
	private String imageUrl;
	private String brief;


	public ShipmentTrackingAction(String trackURL) {
		super();
		super.setShipmentType(ShipmentActionType.LINK.getValue());
		this.trackURL = trackURL;
	}

	@SuppressWarnings("unchecked")
	public void addData(Map<String, Object> nd) {
		ScreenName sn = ScreenName.fromValue(this.trackURL);

		if (nd.containsKey("data")) {
			LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) nd.get("data");

			switch (sn) {

			    case CHAT:
				this.chatId = data.get(CHAT_ID).toString();
				this.avatar = data.get("avatar").toString();
				break;

				case DELIVERY_REQUEST_FEDEX:
				this.carrierId = data.get(CARRIER_ID).toString();
				this.brief = data.get("brief").toString();
				break;

				case DELIVERY_REQUEST_UPS:
					this.shipmentId = data.get(SHIPMENT_ID).toString();
					this.price = data.get("price").toString();
					break;
			default:
				break;
			}
		}
	}

	public String getTrackURL() {
		return trackURL;
	}

	public void setTrackURL(String trackURL) {
		this.trackURL = trackURL;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
