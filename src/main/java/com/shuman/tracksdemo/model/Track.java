package com.shuman.tracksdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Track {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Embedded
    private TrackLength length;
}
