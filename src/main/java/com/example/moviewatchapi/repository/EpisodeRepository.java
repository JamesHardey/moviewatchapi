package com.example.moviewatchapi.repository;

import com.example.moviewatchapi.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

}
