package com.yolifay.movieservice.service;

import com.yolifay.movieservice.common.Constants;
import com.yolifay.movieservice.common.ConstantsProperties;
import com.yolifay.movieservice.common.ResponseService;
import com.yolifay.movieservice.common.ResponseUtil;
import com.yolifay.movieservice.entity.Movie;
import com.yolifay.movieservice.exception.DataNotFoundException;
import com.yolifay.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ConstantsProperties constantsProperties;

    public ResponseService createMovie(Movie movie) {
        log.info("Start creating movie {}", movie.getTitle());
        Movie saved = movieRepository.save(movie);

        log.info("End movie creating: {}", saved.getTitle());
        return ResponseUtil.setResponse(
                HttpStatus.CREATED.value(),
                constantsProperties.getServiceId(),
                Constants.RESPONSE.CREATED,
                saved
        );
    }

    public ResponseService getAllMovies(Pageable pageable) {
        log.info("Start get all movies");

        Page<Movie> movies = movieRepository.findAll(pageable);

        log.info("End get all movies");
        return ResponseUtil.setResponse(
                HttpStatus.OK.value(),
                constantsProperties.getServiceId(),
                Constants.RESPONSE.APPROVED,
                movies
        );
    }

    public ResponseService getMovieById(Long id) {
        log.info("Start get movie by id {}", id);

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("ID " + id + " movie tidak ditemukan"));

        log.info("End get movie by id {}", id);
        return ResponseUtil.setResponse(
                HttpStatus.OK.value(),
                constantsProperties.getServiceId(),
                Constants.RESPONSE.APPROVED,
                movie
        );
    }

    public ResponseService updateMovie(Long id, Movie updatedMovie) {
        log.info("Start update movie with id {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie dengan ID " + id + " tidak ditemukan"));

        movie.setTitle(updatedMovie.getTitle());
        movie.setDescription(updatedMovie.getDescription());
        movie.setReleaseYear(updatedMovie.getReleaseYear());

        Movie saved = movieRepository.save(movie);

        log.info("End update movie with id {}", id);
        return ResponseUtil.setResponse(
                HttpStatus.OK.value(),
                constantsProperties.getServiceId(),
                Constants.RESPONSE.APPROVED,
                saved
        );
    }

    public ResponseService deleteMovie(Long id) {
        log.info("Start delete movie with id {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie dengan ID " + id + " tidak ditemukan"));
        movieRepository.delete(movie);

        log.info("End delete movie with id {}", id);
        return ResponseUtil.setResponse(
                HttpStatus.OK.value(),
                constantsProperties.getServiceId(),
                Constants.RESPONSE.APPROVED,
                "Movie berhasil dihapus"
        );
    }
}
