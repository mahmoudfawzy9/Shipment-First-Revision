package com.company.star.form;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Package implements Form{

    @NotNull(message = "width field is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "500.0", inclusive = false)
    private BigDecimal width;

    @NotNull(message = "height field is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "500.0", inclusive = false)
    private BigDecimal height;

    @NotNull(message = "length field is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "500.0", inclusive = false)
    private BigDecimal length;

    @NotNull(message = "weight field is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "500.0", inclusive = false)
    private BigDecimal weight;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

//	@NotBlank(message = "imei field is required")
//	@Size(min = 15, max = 36, message = "imei must be between 15 and 36 characters")
//	private String imei;

//	public String getImei() {
//		return imei;
//	}
//
//	public void setImei(String imei) {
//		this.imei = imei;
//	}

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }
}
