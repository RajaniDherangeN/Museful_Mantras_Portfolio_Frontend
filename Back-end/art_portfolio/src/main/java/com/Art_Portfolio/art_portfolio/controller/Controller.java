package com.Art_Portfolio.art_portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Art_Portfolio.art_portfolio.model.Artwork;
import com.Art_Portfolio.art_portfolio.service.ArtworkService;

import jakarta.annotation.PostConstruct;

import java.util.List;

import javax.sql.DataSource;

@CrossOrigin(origins = "*") // Allow Angular requests
@RestController
@RequestMapping("/api")
public class Controller {
	@Autowired
    private ArtworkService artworkService;

    // GET all artworks
	@GetMapping("/artworks")
    public List<Artwork> getAllArtworks() {
        List<Artwork> artworks = artworkService.getAllArtworks();

        for (Artwork art : artworks) {
            System.out.println(art.getTitle());
        }

        return artworks;
    }
	
	@Autowired
    private DataSource dataSource;
	
    @PostConstruct
    public void printDbInfo() throws Exception {
		var conn = dataSource.getConnection();
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery(
            "SELECT inet_server_addr(), inet_server_port(), version()"
        );

        while (rs.next()) {
            System.out.println("SERVER IP: " + rs.getString(1));
            System.out.println("SERVER PORT: " + rs.getString(2));
            System.out.println("VERSION: " + rs.getString(3));
        }
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
    
    @GetMapping("/db-check")
    public String checkDb() {
        return artworkService.getAllArtworks().toString();
    }
}
