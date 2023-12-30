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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final EpisodeRepository episodeRepository;

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
    public Page<MovieDTO> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(this::convertToDTO);
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
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        LocalDateTime now = LocalDateTime.now();

        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setUpdatedAt(now);
        movie.setImageUrl(movieDTO.getImageUrl());
        movie.setYoutubeUrl(movieDTO.getYoutubeUrl());

        List<EpisodeDTO> episodesDtos = movieDTO.getEpisodes();
        List<Episode> episodes = episodesDtos.stream()
                .map(episodeDTO -> {
                    episodeDTO.setUploadedAt(now);
                    return MovieMapper.mapToEntity(episodeDTO);
                })
                .collect(Collectors.toList());

        movie.setEpisodes(episodes);
        movie.setUpdatedAt(now);

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

    @Override
    public void deleteEpisodeFromMovie(Integer movieId, Integer episodeId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow();

        Episode episodeToDelete = movie.getEpisodes().stream()
                .filter(episode -> Objects.equals(episode.getId(), episodeId))
                .findFirst()
                .orElseThrow();

        movie.getEpisodes().remove(episodeToDelete);
        movieRepository.save(movie);

        episodeRepository.delete(episodeToDelete);
    }

}
