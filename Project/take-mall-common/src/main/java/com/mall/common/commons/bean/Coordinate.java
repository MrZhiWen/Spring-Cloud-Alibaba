package com.mall.common.commons.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coordinate implements Serializable {

    private BigDecimal longitude;

    private BigDecimal latitude;

    public Coordinate(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinate() {
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}