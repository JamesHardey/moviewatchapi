package com.example.moviewatchapi.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovieDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
    private String youtubeUrl;
    private List<EpisodeDTO> episodes;
}
