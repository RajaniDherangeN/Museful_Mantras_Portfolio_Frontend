package com.portfolio_backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio_backend.backend.model.ArtWork;
import com.portfolio_backend.backend.service.ArtworkService;

@CrossOrigin(origins = "*") // Allow Angular requests
@RestController
@RequestMapping("/api")
public class Controller {
	@Autowired
    private ArtworkService artworkService;

    // GET all artWorks
    /*@GetMapping("/artworks")
    public List<ArtWork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }*/
	@GetMapping("/artworks")
    public List<ArtWork> getAllArtworks() {
        List<ArtWork> artworks = artworkService.getAllArtworks();

        for (ArtWork art : artworks) {
            System.out.println(art.getTitle());
        }

        return artworks;
    }
    
    @GetMapping("/random-artworks")
    public List<ArtWork> getRandomArtworks() {
        return artworkService.getRandomArtworks();
    }

    // POST save artwork
    @PostMapping
    public ArtWork saveArtwork(@RequestBody ArtWork artwork) {
        return artworkService.saveArtwork(artwork);
    }

    // PUT update likes
    @PutMapping("/{id}/like")
    public ArtWork likeArtwork(@PathVariable Long id) {
        return artworkService.updateLikes(id);
    }
}