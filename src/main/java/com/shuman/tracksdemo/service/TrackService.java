package com.shuman.tracksdemo.service;

import com.shuman.tracksdemo.model.Track;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TrackService {
    public List<Track> getTracks() {
        return Collections.emptyList();
    }

    public Track createTrack(Track track) {
        return track;
    }

    public Track updateTrack(Track trackToUpdate) {
        return trackToUpdate;
    }

    public void removeTrack(UUID trackToRemoveId) {

    }
}
