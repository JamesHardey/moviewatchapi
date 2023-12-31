package com.example.moviewatchapi.controller;

import com.example.moviewatchapi.dto.CreateEpisodeDTO;
import com.example.moviewatchapi.dto.CreateMovieDTO;
import com.example.moviewatchapi.dto.MovieDTO;
import com.example.moviewatchapi.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    private MovieService movieService;

    public MovieController(
            MovieService movieService
    ){
        this.movieService = movieService;
    }
    // Method to get all movies
    @GetMapping
    public ResponseEntity<Page<MovieDTO>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MovieDTO> movies = movieService.getAllMovies(pageRequest);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // Method to get a movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Integer id) {
        MovieDTO movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // Method to save a movie
    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody CreateMovieDTO movieDTO) {
        MovieDTO movie = movieService.saveMovie(movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    // Method to update a movie
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Integer id, @RequestBody MovieDTO movieDTO) {
        MovieDTO movie = movieService.updateMovie(id, movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @PutMapping("/{movieId}/episodes")
    public ResponseEntity<MovieDTO> addEpisodeToMovie(
            @PathVariable Integer movieId,
            @RequestBody CreateEpisodeDTO episodesDTO) {
        MovieDTO movie = movieService.addEpisodeToMovie(movieId, episodesDTO);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    // Method to delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{movieId}/episode/{episodeId}")
    public ResponseEntity<Void> deleteEpisodeFromMovie(
            @PathVariable Integer movieId,
            @PathVariable Integer episodeId
    ){
        movieService.deleteEpisodeFromMovie(movieId,episodeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

