package com.company.star.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import com.company.star.form.CarrierForm;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author m.fawzy
 */
@Entity
@Table(name = "carrier")

public class Carrier implements Serializable {
	private static final long serialVersionUID = -6260470480495807682L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "carrier_id", nullable = false)
	private int carrierId;

	@Column(name = "carrier_service_id", nullable = false)
	private String carrierServiceID;

	@Column(name = "carrier_type", nullable = false)
	private int carrierType;

//	@Column(name = "shipment_type", nullable = false)
//	private int shipmentType;

//	@Column(name = "device_type", nullable = false)
//	private int deviceType;

	@Column(name = "action_type", nullable = false)
	private int actionType;

	@Column(name = "action_value", nullable = false)
	private String actionValue;

	@Column(name = "length", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal length;

	@Column(name = "width", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal width;

	@Column(name = "height", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal height;

	@Column(name = "weight", nullable = false)
	@Digits(integer=3, fraction=3)
	private BigDecimal weight;

	@Column(name = "carrier", nullable = false)
	private String carrier;

//	List<String> tokens ;   // How to get this ?
//
//	public List<String> getTokens() {
//		return tokens;
//	}
//
//	public void setTokens(List<String> tokens) {
//		this.tokens = tokens;
//	}

	@Column(name = "is_confirmed", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean isConfirmed = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 26, insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, length = 26, insertable = false, columnDefinition = "timestamp default current_timestamp")
	private Date updatedAt;

	@Column(name = "is_deleted", nullable = false, insertable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isDeleted;

	@Column(name = "is_delivered", nullable = false, insertable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isDelivered;

	public Carrier() {
	}

	public Carrier(Integer id) {
		this.id = id;
	}

	public Carrier(int carrierId, String carrierServiceID, String actionValue, int carrierType, int shipmentType,BigDecimal weight, BigDecimal length, BigDecimal width, BigDecimal height, String carrier,
				   boolean isConfirmed, Date createdAt, Date updatedAt, boolean isDeleted, boolean isDelivered) {
		this.carrierId = carrierId;
		this.carrierType = carrierType;
//		this.shipmentType = shipmentType;
		this.actionValue = actionValue;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.carrier = carrier;
		this.carrierServiceID = carrierServiceID;
		this.isConfirmed = isConfirmed;
		this.isDelivered = isDelivered;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isDeleted = isDeleted;
	}

	public Carrier(CarrierForm obj) {
		this.carrierId = obj.getCarrierId();
		this.carrierServiceID = obj.getCarrierServiceID();
		this.carrierType = obj.getCarrierType(2);
//		this.shipmentType = obj.getShipmentType();
		this.actionValue = obj.getActionValue();
		this.length = obj.getLength();
		this.width = obj.getWidth();
		this.height = obj.getHeight();
		this.weight = obj.getWeight();
		this.carrier = obj.getCarrierServiceID();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarrierId(int carrierId) {
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

//	public int getDeviceType() {
//		return deviceType;
//	}
//
//	public void setDeviceType(int deviceType) {
//		this.deviceType = deviceType;
//	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

//	public int getShipmentType() {
//		return shipmentType;
//	}
//
//	public void setShipmentType(int shipmentType) {
//		this.shipmentType = shipmentType;
//	}


	public boolean isDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(boolean delivered) {
		isDelivered = delivered;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}
	public void setConfirmed(boolean confirmed) {
		isConfirmed = confirmed;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getCarrierType() {
		return carrierType;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public String getCarrierServiceID() {
		return carrierServiceID;
	}

	public void setCarrierServiceID(String carrierServiceID) {
		this.carrierServiceID = carrierServiceID;
	}
}
