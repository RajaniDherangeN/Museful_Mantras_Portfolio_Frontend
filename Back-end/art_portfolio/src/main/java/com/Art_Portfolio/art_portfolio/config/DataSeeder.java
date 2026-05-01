package com.Art_Portfolio.art_portfolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Art_Portfolio.art_portfolio.model.Artwork;
import com.Art_Portfolio.art_portfolio.repository.ArtworkRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ArtworkRepository repo) {
        return args -> {

            repo.save(createArtwork(
                "Sunset Painting",
                "https://example.com/sunset.jpg",
                "A beautiful sunset over mountains",
                120,
                "Nature"
            ));

            repo.save(createArtwork(
                "Modern Abstract",
                "https://example.com/abstract.jpg",
                "Colorful abstract digital art",
                85,
                "Abstract"
            ));

            repo.save(createArtwork(
                "Portrait Sketch",
                "https://example.com/portrait.jpg",
                "Hand-drawn pencil portrait",
                60,
                "Portrait"
            ));

        };
    }

    private Artwork createArtwork(String title, String imageUrl, String description, Integer likes, String category) {
        Artwork a = new Artwork();
        a.setTitle(title);
        a.setImageUrl(imageUrl);
        a.setDescription(description);
        a.setLikes(likes);
        a.setCategory(category);
        return a;
    }
}