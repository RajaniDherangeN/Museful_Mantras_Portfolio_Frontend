package com.Art_Portfolio.art_portfolio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.Art_Portfolio.art_portfolio.repository.ArtworkRepository;
import com.Art_Portfolio.art_portfolio.model.Artwork;

import org.springframework.stereotype.Service;

@Service
public class ArtworkService {
	 @Autowired
	    private ArtworkRepository artworkRepository;

	    public List<Artwork> getAllArtworks() {
	        return artworkRepository.findAll();
	    }

	    public Artwork saveArtwork(Artwork artwork) {
	        return artworkRepository.save(artwork);
	    }

	    public Artwork updateLikes(Long id) {
	        Artwork artwork = artworkRepository.findById(id).orElseThrow();
	        artwork.setLikes(artwork.getLikes() + 1);
	        return artworkRepository.save(artwork);
	    }

		public List<Artwork> getRandomArtworks() {
			  return artworkRepository.getRandomArtworks();
		}
}
