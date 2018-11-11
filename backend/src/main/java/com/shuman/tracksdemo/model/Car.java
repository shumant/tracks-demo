package com.shuman.tracksdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
public class Car {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private TransmissionType transmission;
    private AiState ai;

    @JsonProperty("max_speed")
    @Embedded
    private CarSpeed maxSpeed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Track track;
}
