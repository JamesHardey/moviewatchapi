package com.example.moviewatchapi.service;

import com.example.moviewatchapi.dto.CreateEpisodeDTO;
import com.example.moviewatchapi.dto.CreateMovieDTO;
import com.example.moviewatchapi.dto.MovieDTO;
import com.example.moviewatchapi.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovieService {

   Page<MovieDTO> getAllMovies(Pageable pageable);
   MovieDTO getMovieById(Integer id);

   MovieDTO saveMovie(CreateMovieDTO movieDTO);

   MovieDTO updateMovie(Integer id, MovieDTO movieDTO);

    MovieDTO addEpisodeToMovie(Integer movieId,CreateEpisodeDTO episodesDTO);

   void deleteMovie(Integer id);

   void deleteEpisodeFromMovie(Integer movieId, Integer episodeId);

}
