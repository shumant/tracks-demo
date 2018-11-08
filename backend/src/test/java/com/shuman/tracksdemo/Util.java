package com.shuman.tracksdemo;

import com.shuman.tracksdemo.model.*;

public class Util {

    private Util() {
    }

    public static Track track() {
        return new Track()
            .setName("Millbrook")
            .setDescription("Millbrook city course race track")
            .setLength(new TrackLength()
                .setUnit(LengthUnit.KM)
                .setValue(7.4)
            );
    }

    public static Car car1(Track track) {
        return new Car()
            .setTrack(track)
            .setCode("rdb1")
            .setTransmission(TransmissionType.AUTOMATIC)
            .setAi(AiState.ENABLED)
            .setMaxSpeed(new CarSpeed()
                .setUnit(SpeedUnit.MPS)
                .setValue(110.12121212));
    }

    public static Car car2(Track track) {
        return new Car()
            .setTrack(track)
            .setCode("rdb3")
            .setTransmission(TransmissionType.AUTOMATIC)
            .setAi(AiState.DISABLED)
            .setMaxSpeed(new CarSpeed()
                .setUnit(SpeedUnit.MPS)
                .setValue(120.967));
    }
}
