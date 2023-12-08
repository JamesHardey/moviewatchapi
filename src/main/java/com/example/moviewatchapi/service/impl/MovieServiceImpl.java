package com.example.moviewatchapi.service.impl;

import com.example.moviewatchapi.dto.CreateEpisodeDTO;
import com.example.moviewatchapi.dto.CreateMovieDTO;
import com.example.moviewatchapi.dto.EpisodeDTO;
import com.example.moviewatchapi.dto.MovieDTO;
import com.example.moviewatchapi.model.Episode;
import com.example.moviewatchapi.model.Movie;
import com.example.moviewatchapi.repository.EpisodeRepository;
import com.example.moviewatchapi.repository.MovieRepository;
import com.example.moviewatchapi.service.MovieService;
import com.example.moviewatchapi.util.MovieMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private EpisodeRepository episodeRepository;

   public MovieServiceImpl(
           MovieRepository movieRepository,
           EpisodeRepository episodeRepository
   ){
       this.movieRepository = movieRepository;
       this.episodeRepository = episodeRepository;
   }

    // Method to convert a Movie entity to a MovieDTO
    private MovieDTO convertToDTO(Movie movie) {
        return MovieMapper.mapToDTO(movie);
    }

    // Method to convert a MovieDTO to a Movie entity
    private Movie convertToEntity(MovieDTO movieDTO) {
        return MovieMapper.mapToEntity(movieDTO);
    }
    private Movie convertToEntity(CreateMovieDTO movieDTO) {
        return MovieMapper.mapToEntity(movieDTO);
    }


    // Method to get all movies from the database
    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get a movie by id from the database
    @Override
    public MovieDTO getMovieById(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return convertToDTO(movie);
    }

    // Method to save a movie to the database
    @Override
    public MovieDTO saveMovie(CreateMovieDTO movieDTO) {
        Movie movie = convertToEntity(movieDTO);
        movie.setCreatedAt(LocalDateTime.now());
        movie.setUpdatedAt(LocalDateTime.now());
        movie = movieRepository.save(movie);
        return convertToDTO(movie);
    }

    // Method to update a movie in the database
    @Override
    public MovieDTO updateMovie(Integer id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(LocalDateTime.now());
        movie.setImageUrl(movieDTO.getImageUrl());
        movie.setYoutubeUrl(movieDTO.getYoutubeUrl());

        List<EpisodeDTO> episodesDtos = movieDTO.getEpisodes();
        movie.setEpisodes(episodesDtos.stream().map(MovieMapper::mapToEntity)
                .collect(Collectors.toList()));
        movie = movieRepository.save(movie);
        return convertToDTO(movie);
    }

    @Override
    public MovieDTO addEpisodeToMovie(Integer movieId, CreateEpisodeDTO episodesDTO) {
       var dateNow = LocalDateTime.now();
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Episode episode = MovieMapper.mapToEntity(episodesDTO);
        episode.setUploadedAt(dateNow);
        Episode episode1 = episodeRepository.save(episode);
        movie.getEpisodes().add(episode1);
        movie.setUpdatedAt(dateNow);
        Movie movie1 = movieRepository.save(movie);
        return MovieMapper.mapToDTO(movie1);
    }

    // Method to delete a movie from the database
    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
