package com.portfolio_backend.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio_backend.backend.model.ArtWork;


public interface ArtworkRepository extends JpaRepository<ArtWork, Long>{
    @Query(value = "SELECT * FROM artworks ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<ArtWork> getRandomArtworks();
}
