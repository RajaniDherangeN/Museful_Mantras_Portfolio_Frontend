package com.Art_Portfolio.art_portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Art_Portfolio.art_portfolio.model.Artwork;
import com.Art_Portfolio.art_portfolio.service.ArtworkService;
import java.util.List;

@CrossOrigin(origins = "*") // Allow Angular requests
@RestController
@RequestMapping("/api")
public class Controller {
	@Autowired
    private ArtworkService artworkService;

    // GET all artworks
    @GetMapping("/artworks")
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }
    
    @GetMapping("/random-artworks")
    public List<Artwork> getRandomArtworks() {
        return artworkService.getRandomArtworks();
    }

    // POST save artwork
    @PostMapping
    public Artwork saveArtwork(@RequestBody Artwork artwork) {
        return artworkService.saveArtwork(artwork);
    }

    // PUT update likes
    @PutMapping("/{id}/like")
    public Artwork likeArtwork(@PathVariable Long id) {
        return artworkService.updateLikes(id);
    }
}

