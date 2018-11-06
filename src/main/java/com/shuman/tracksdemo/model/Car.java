package com.shuman.tracksdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Car {
    private UUID id;
    private String code;
    private TransmissionType transmission;
    private Boolean ai;

    @JsonProperty("max-speed")
    private CarSpeed maxSpeed;
}
