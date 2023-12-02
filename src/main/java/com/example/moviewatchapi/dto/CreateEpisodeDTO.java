package com.example.moviewatchapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEpisodeDTO {
    private String title;
    private Integer episodeNumber;
    private String downloadUrl;
}
