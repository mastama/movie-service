package com.yolifay.movieservice.controller;

import com.yolifay.movieservice.common.ResponseService;
import com.yolifay.movieservice.entity.Movie;
import com.yolifay.movieservice.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<ResponseService> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        int zeroBased = Math.max(page - 1, 0);
        Pageable pageable = PageRequest.of(zeroBased, size);
        return ResponseEntity.ok(movieService.getAllMovies(pageable));
    }

    @PostMapping
    public ResponseEntity<ResponseService> createMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseService> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseService> updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody Movie movie
    ) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}

