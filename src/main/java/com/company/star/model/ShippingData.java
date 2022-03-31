package com.company.star.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ShippingData {
	private String apartment;
	private String email;
	private Integer floor;
	private String firstName;
	private String lastName;
	private String street;
	private Integer building;
	private String phoneNumber;
	private String postalCode;
	private String extraDescription;
	private String city;
	private String country;
	private String state;

	private Integer id;
	private Integer orderId;
	private Integer order;
	private String shippingMethod;
	private String linkUrl;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getExtraDescription() {
		return extraDescription;
	}

	public void setExtraDescription(String extraDescription) {
		this.extraDescription = extraDescription;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

}
