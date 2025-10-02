package com.yolifay.movieservice.service;

import com.yolifay.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieService {
    private final MovieRepository movieRepository;


}
