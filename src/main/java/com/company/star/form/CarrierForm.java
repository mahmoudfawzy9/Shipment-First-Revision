package com.company.star.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarrierForm extends PackageDetails {

	@NotNull(message = "carrier id field is required")
	@Min(value = 1, message = "carrier id should be bigger than or equal to one")
	private int carrierId;

	@NotNull(message = "carrier type field is required")
	@Min(value = 1, message = "carrier type should be 1,2 or 3")
	@Max(value = 3, message = "carrier type should be 1,2 or 3")
	private int carrierType;

	@NotBlank(message = "actionValue field is required")
	private String actionValue;
//	@NotNull(message = "shipment type field is required")
//	@Min(value = 1, message = "carrier type should be 1,2 or 3")
//	@Max(value = 3, message = "carrier type should be 1,2 or 3")
//	private int shipmentType;

	@NotBlank(message = "carrier field is required")
	private String carrierServiceID;


	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public int getCarrierType(int i) {
		return carrierType;
	}

	public void setCarrierType(int carrierType) {
		this.carrierType = carrierType;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	//	public int getShipmentType() {
//		return shipmentType;
//	}
//
//	public void setShipmentType(int shipmentType) {
//		this.shipmentType = shipmentType;
//	}


	public String getCarrierServiceID() {
		return carrierServiceID;
	}

	public void setCarrierServiceID() {
		this.carrierServiceID = carrierServiceID;
	}
}
