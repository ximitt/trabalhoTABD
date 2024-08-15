package com.example.atividade05.controller;

import com.example.atividade05.model.Show;
import com.example.atividade05.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @PostMapping
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        Show savedShow = showRepository.save(show);
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showRepository.findAll();
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }
}
