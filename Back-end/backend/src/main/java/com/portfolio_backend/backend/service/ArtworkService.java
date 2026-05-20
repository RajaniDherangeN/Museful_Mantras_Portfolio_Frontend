package com.portfolio_backend.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.portfolio_backend.backend.model.ArtWork;
import com.portfolio_backend.backend.repository.ArtworkRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtworkService {
	@Autowired
    private ArtworkRepository artworkRepository;

    public List<ArtWork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    public ArtWork saveArtwork(ArtWork artwork) {
        return artworkRepository.save(artwork);
    }

    public ArtWork updateLikes(Long id) {
        ArtWork artwork = artworkRepository.findById(id).orElseThrow();
        artwork.setLikes(artwork.getLikes() + 1);
        return artworkRepository.save(artwork);
    }

	public List<ArtWork> getRandomArtworks() {
		  return artworkRepository.getRandomArtworks();
	}
}
