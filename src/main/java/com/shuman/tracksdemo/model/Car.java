package com.shuman.tracksdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private TransmissionType transmission;
    private Boolean ai;

    @JsonProperty("max-speed")
    @Embedded
    private CarSpeed maxSpeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonIgnore
    private Track track;


}
