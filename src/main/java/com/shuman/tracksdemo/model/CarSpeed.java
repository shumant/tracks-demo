package com.shuman.tracksdemo.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class CarSpeed {
    private SpeedUnit unit;
    private Double value;
}
