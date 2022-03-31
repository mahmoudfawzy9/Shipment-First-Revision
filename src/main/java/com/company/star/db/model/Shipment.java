package com.company.star.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;

//import com.company.star.enums.PackageDetails;
import com.company.star.form.ShipmentForm;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author m.fawzy
 */
@Entity
@Table(name = "shipment")

public class Shipment implements Serializable {
	private static final long serialVersionUID = -7836495428837315109L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "carrier_id", nullable = false)
	private int carrierId;

	@Column(name = "shipment_service_id", nullable = false)
	private String shipmentServiceID;

	@Column(name = "carrier_type", nullable = false)
	private int carrierType;

//	@Enumerated(EnumType.STRING)
//	private PackageDetails packageDetails;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "carrier", nullable = false)
	private String carrier;

//	@Column(name = "status", nullable = false)
//	private String status;

	@Column(name = "action_type", nullable = false)
	private int actionType;

	@Column(name = "action_value", length = 500)
	private String actionValue;

	@Column(name = "weight", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal weight;

	@Column(name = "height", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal height;

	@Column(name = "width", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal width;

	@Column(name = "length", nullable = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal length;
	
	@Column(name = "is_deleted", nullable = false, insertable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 26, insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, length = 26, insertable = false, columnDefinition = "timestamp default current_timestamp")
	private Date updatedAt;

	@Column(name = "is_delivered", nullable = false, insertable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isDelivered;

	@Column(name = "is_confirmed", nullable = false, insertable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isConfirmed;

	public Shipment() {
	}

	public Shipment(int id) {
		this.id = id;
	}

	public Shipment(int id, int carrierId, String status, boolean isDelivered, String shipmentServiceID,String carrier,BigDecimal height,BigDecimal width,BigDecimal length, BigDecimal weight,  int carrierType, int actionType, String actionValue) {
		this.id = id;
		this.carrierId = carrierId;
		this.shipmentServiceID = shipmentServiceID;
		this.carrier = carrier;
		this.carrierType = carrierType;
		this.actionType = actionType;
		this.actionValue = actionValue;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.isDelivered = isDelivered;
//		this.status = status;
	}

	public Shipment(ShipmentForm form) {
		this.carrierId = form.getCarrierId(1);
		this.carrierType = form.getCarrierType(2);
		this.carrier = form.getCarrier();
		this.actionType = form.getActionType();
		if(form.getActionValue() != null) {
			this.actionValue = form.getActionValue().toString();
		}
		this.name = form.getName();
		this.weight = form.getWeight();
		this.height = form.getHeight();
		this.width = form.getWidth();
		this.length = form.getLength();
		this.shipmentServiceID = form.getshipmentServiceID();
//		this.name = form.getLocalizedTitle();
//		this.status = form.getStatus();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

//	public int getCarrierType(int i) {
//		return carrierType;
//	}
//
//	public void setUserType(int carrierType) {
//		this.carrierType = carrierType;
//	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

//	public String getStatus() {
//		return status;
//	}

//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getCarrierType(int i) {
		return carrierType;
	}

	public void setCarrierType(int carrierType) {
		this.carrierType = carrierType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public boolean getIsDelivered() {
		return this.isDelivered;
	}

	public void setIsDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
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

	public boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean delivered) {
		isDelivered = delivered;
	}

	public String getShipmentServiceID() {
		return shipmentServiceID;
	}

	public void setShipmentServiceID(String shipmentServiceID) {
		this.shipmentServiceID = shipmentServiceID;
	}
}
