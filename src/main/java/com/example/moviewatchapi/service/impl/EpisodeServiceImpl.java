package com.example.moviewatchapi.service.impl;

import com.example.moviewatchapi.dto.EpisodeDTO;
import com.example.moviewatchapi.model.Episode;
import com.example.moviewatchapi.repository.EpisodeRepository;
import com.example.moviewatchapi.service.EpisodeService;
import com.example.moviewatchapi.util.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    private EpisodeRepository episodeRepository;

    // Method to convert an Episode entity to an EpisodeDTO
    private EpisodeDTO convertToDTO(Episode episode) {
        return MovieMapper.mapToDTO(episode);
    }

    // Method to convert an EpisodeDTO to an Episode entity
    private Episode convertToEntity(EpisodeDTO episodeDTO) {
        return MovieMapper.mapToEntity(episodeDTO);
    }

    // Method to get all episodes from the database
    @Override
    public List<EpisodeDTO> getAllEpisodes() {
        List<Episode> episodes = episodeRepository.findAll();
        return episodes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an episode by id from the database
    @Override
    public EpisodeDTO getEpisodeById(Integer id) {
        Episode episode = episodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Episode not found"));
        return convertToDTO(episode);
    }

    // Method to save an episode to the database
    @Override
    public EpisodeDTO saveEpisode(EpisodeDTO episodeDTO) {
        Episode episode = convertToEntity(episodeDTO);
        episode = episodeRepository.save(episode);
        return convertToDTO(episode);
    }

    // Method to update an episode in the database
    @Override
    public EpisodeDTO updateEpisode(Integer id, EpisodeDTO episodeDTO) {
        Episode episode = episodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Episode not found"));
        episode.setTitle(episodeDTO.getTitle());
        episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
        episode.setDownloadUrl(episodeDTO.getDownloadUrl());
        episode.setUploadedAt(episodeDTO.getUploadedAt());
        episode = episodeRepository.save(episode);
        return convertToDTO(episode);
    }

    // Method to delete an episode from the database
    @Override
    public void deleteEpisode(Integer id) {
        episodeRepository.deleteById(id);
    }
}
