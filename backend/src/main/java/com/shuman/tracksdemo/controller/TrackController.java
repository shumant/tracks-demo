package com.shuman.tracksdemo.controller;

import com.shuman.tracksdemo.model.Track;
import com.shuman.tracksdemo.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/tracks")
    public List<Track> getTracks() {
        return trackService.getTracks();
    }

    @PostMapping("/tracks")
    public Track createTrack(@RequestBody Track track) {
        return trackService.createTrack(track);
    }

    @PutMapping("/tracks/{id}")
    public Track updateTrack(@PathVariable("id") UUID trackId, @RequestBody Track track) {
        track.setId(trackId);
        return trackService.updateTrack(track);
    }

    @DeleteMapping("/tracks/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") UUID trackId) {
        trackService.removeTrack(trackId);
        return ResponseEntity.ok().build();
    }
}
