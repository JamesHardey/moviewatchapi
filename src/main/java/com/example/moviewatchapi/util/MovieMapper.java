package com.example.moviewatchapi.util;

import com.example.moviewatchapi.dto.CreateEpisodeDTO;
import com.example.moviewatchapi.dto.CreateMovieDTO;
import com.example.moviewatchapi.dto.EpisodeDTO;
import com.example.moviewatchapi.dto.MovieDTO;
import com.example.moviewatchapi.model.Episode;
import com.example.moviewatchapi.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static MovieDTO mapToDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setYoutubeUrl(movie.getYoutubeUrl());
        movieDTO.setImageUrl(movie.getImageUrl());
        movieDTO.setCreatedAt(movie.getCreatedAt());
        movieDTO.setUpdatedAt(movie.getUpdatedAt());
        movieDTO.setDescription(movie.getDescription());

        List<EpisodeDTO> episodeList = new ArrayList<>();
        movie.getEpisodes().forEach(episode -> {
            var episode1 = new EpisodeDTO(
                    episode.getId(),
                    episode.getTitle(),
                    episode.getEpisodeNumber(),
                    episode.getDownloadUrl(),
                    episode.getUploadedAt()
            );
            episodeList.add(episode1);
        });

        movieDTO.setEpisodes(episodeList);
        return movieDTO;
    }

    public static  Movie mapToEntity(CreateMovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setYoutubeUrl(movieDTO.getYoutubeUrl());
        movie.setImageUrl(movieDTO.getImageUrl());
        movie.setDescription(movieDTO.getDescription());
        return movie;
    }


    public static  Movie mapToEntity(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setYoutubeUrl(movieDTO.getYoutubeUrl());
        movie.setImageUrl(movieDTO.getImageUrl());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        movie.setDescription(movieDTO.getDescription());

        movie.setEpisodes(movieDTO.getEpisodes().stream().map(episode -> new Episode(
                episode.getId(),
                episode.getTitle(),
                episode.getEpisodeNumber(),
                episode.getDownloadUrl(),
                episode.getUploadedAt()
        )).collect(Collectors.toList()));

        return movie;
    }

    public static EpisodeDTO mapToDTO(Episode episode){
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setId(episode.getId());
        episodeDTO.setTitle(episode.getTitle());
        episodeDTO.setEpisodeNumber(episode.getEpisodeNumber());
        episodeDTO.setDownloadUrl(episode.getDownloadUrl());
        episodeDTO.setUploadedAt(episode.getUploadedAt());
        return episodeDTO;
    }

    public static Episode mapToEntity(EpisodeDTO episodeDTO){
        Episode episode = new Episode();
        episode.setId(episodeDTO.getId());
        episode.setTitle(episodeDTO.getTitle());
        episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
        episode.setDownloadUrl(episodeDTO.getDownloadUrl());
        episode.setUploadedAt(episodeDTO.getUploadedAt());
        return episode;
    }


    public static Episode mapToEntity(CreateEpisodeDTO episodeDTO){
        Episode episode = new Episode();
        episode.setTitle(episodeDTO.getTitle());
        episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
        episode.setDownloadUrl(episodeDTO.getDownloadUrl());
        return episode;
    }

}
