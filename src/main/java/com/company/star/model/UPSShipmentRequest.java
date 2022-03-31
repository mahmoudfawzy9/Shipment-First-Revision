package com.company.star.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UPSShipmentRequest {
    private String shipmentServiceID;
    private Integer numberOfPackages;
    private Integer weight;
    private Integer length;
    private Integer width;
    private Integer height;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getShipmentServiceID() {
        return shipmentServiceID;
    }

    public void setShipmentServiceID(String shipmentServiceID) {
        this.shipmentServiceID = shipmentServiceID;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getNumberOfPackages() {
        return numberOfPackages;
    }

    public void setNumberOfPackages(Integer numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
