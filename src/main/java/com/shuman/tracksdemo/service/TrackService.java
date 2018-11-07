package com.shuman.tracksdemo.service;

import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getTracks() {
        return trackRepository.findAll();
    }

    public Track createTrack(Track track) {
        if (track.getId() != null) {
            throw new IllegalStateException("Cannot create a trackId with id");
        }

        return trackRepository.save(track);
    }

    public Track updateTrack(Track trackToUpdate) {
        if (!trackRepository.existsById(trackToUpdate.getId())) {
            throw new IllegalStateException(String.format("Track with provided ID [%s] is not present", trackToUpdate.getId().toString()));
        }
        return trackRepository.save(trackToUpdate);
    }

    public void removeTrack(UUID trackToRemoveId) {
        trackRepository.deleteById(trackToRemoveId);
    }
}
