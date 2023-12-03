package com.example.moviewatchapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
    private String youtubeUrl;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Episode> episodes = new ArrayList<>();
}
