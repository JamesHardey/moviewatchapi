package com.example.moviewatchapi.service;

import com.example.moviewatchapi.dto.CreateEpisodeDTO;
import com.example.moviewatchapi.dto.CreateMovieDTO;
import com.example.moviewatchapi.dto.MovieDTO;
import com.example.moviewatchapi.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovieService {

   List<MovieDTO> getAllMovies();
   MovieDTO getMovieById(Integer id);

   MovieDTO saveMovie(CreateMovieDTO movieDTO);

   MovieDTO updateMovie(Integer id, MovieDTO movieDTO);

    MovieDTO addEpisodeToMovie(Integer movieId,CreateEpisodeDTO episodesDTO);

   void deleteMovie(Integer id);

}
