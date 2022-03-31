package com.company.star.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ShipmentForm extends Package implements Form {

	private int carrierId;

	@NotNull(message = "shipmentId is required")
	private String shipmentServiceID;

	@NotNull(message = "carrier is required")
	private String carrier;

	@NotNull(message = "name is required")
	private String name;

	@NotNull(message = "carrierType is required")
	@Min(value = 0, message = "carrierType should be bigger than or equal to one")
	@Max(value = 3,  message = "carrierType should be bigger than or equal to one")
	private int carrierType;

	@NotNull(message = "actionType is required")
	private int actionType;

	@NotNull(message = "actionValue is required")
	private Object actionValue;

//	@NotNull(message = "status is required")
//	private String status;
	private String localizedTitle;
	private String localizedBody;

	public String getshipmentServiceID() {
		return shipmentServiceID;
	}

	public void setshipmentServiceID() {
		this.shipmentServiceID = shipmentServiceID;
	}

	public int getCarrierType(int i) {
		return carrierType;
	}

	public int getCarrierId(int i) {
		return carrierId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public void setCarrierType(int carrierType) {
		this.carrierType = carrierType;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Object getActionValue() {
		return actionValue;
	}

	public void setActionValue(Object actionValue) {
		this.actionValue = actionValue;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getLocalizedTitle() {
		return localizedTitle;
	}

	public void setLocalizedTitle(String localizedTitle) {
		this.localizedTitle = localizedTitle;
	}

	public String getLocalizedBody() {
		return localizedBody;
	}

	public void setLocalizedBody(String localizedBody) {
		this.localizedBody = localizedBody;
	}

}
