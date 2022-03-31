package com.company.star.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FedexShipmentRequest {
    private String carrierServiceID;
    private Integer numberOfPackages;
    private Double weight;
    private Double length;
    private Double width;
    private Double height;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCarrierServiceID() {
        return carrierServiceID;
    }

    public void setCarrierServiceID(String carrierServiceID) {
        this.carrierServiceID = carrierServiceID;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getNumberOfPackages() {
        return numberOfPackages;
    }

    public void setNumberOfPackages(Integer numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
