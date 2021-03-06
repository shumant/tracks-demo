package com.shuman.tracksdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Accessors(chain = true)
public class TrackLength {
    private LengthUnit unit;
    private Double value;
}
