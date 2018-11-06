package com.shuman.tracksdemo.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class TrackLength {
    private LengthUnit unit;
    private Double value;
}
