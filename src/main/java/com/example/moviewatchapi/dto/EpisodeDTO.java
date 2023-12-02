package com.example.moviewatchapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeDTO {
    private Integer id;
    private String title;
    private Integer episodeNumber;
    private String downloadUrl;
    private LocalDateTime uploadedAt;
}