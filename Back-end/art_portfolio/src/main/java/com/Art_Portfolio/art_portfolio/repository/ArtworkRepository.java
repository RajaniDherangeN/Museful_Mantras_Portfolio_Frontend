package com.Art_Portfolio.art_portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Art_Portfolio.art_portfolio.model.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    @Query(value = "SELECT * FROM artworks ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Artwork> getRandomArtworks();
}
