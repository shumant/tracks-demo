package com.shuman.tracksdemo.controller;

import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<Track> getTracks() {
        return trackService.getTracks();
    }

    @PostMapping
    public Track createTrack(Track track) {
        return trackService.createTrack(track);
    }

    @PutMapping("${id}")
    public Track updateTrack(@PathVariable("id") UUID trackId, @RequestBody Track track) {
        if (!Objects.equals(trackId, track.getId())) {
            throw new IllegalArgumentException("Path 'ID' and track 'ID' do not match");
        }
        return trackService.updateTrack(track);
    }

    @DeleteMapping("${id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") UUID trackId) {
        trackService.removeTrack(trackId);
        return ResponseEntity.ok().build();
    }
}
