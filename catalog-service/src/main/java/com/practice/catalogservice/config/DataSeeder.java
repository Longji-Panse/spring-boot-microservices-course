package com.practice.catalogservice.config;

import com.practice.catalogservice.model.Product;
import com.practice.catalogservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) return;

        // A curated list of "Featured" books to start with
        var featuredBooks = List.of(
                new Product(null, "BK-001", "Clean Code", "A Handbook of Agile Software Craftsmanship.", "https://images.example.com/clean-code.jpg", new BigDecimal("45.50")),
                new Product(null, "BK-002", "The Pragmatic Programmer", "Your journey to mastery.", "https://images.example.com/pragmatic.jpg", new BigDecimal("50.00")),
                new Product(null, "BK-003", "Designing Data-Intensive Applications", "The big ideas behind reliable systems.", "https://images.example.com/ddia.jpg", new BigDecimal("59.99")),
                new Product(null, "BK-004", "Spring Microservices in Action", "Build and deploy microservices.", "https://images.example.com/spring-ms.jpg", new BigDecimal("42.00"))
        );

        productRepository.saveAll(featuredBooks);

        // Generate the remaining 36 books with more realistic titles
        var genres = List.of("Fiction", "Mystery", "Sci-Fi", "History", "Biography");

        var randomBooks = IntStream.rangeClosed(5, 40).mapToObj(i -> {
            String genre = genres.get(i % genres.size());
            return new Product(
                    null,
                    "BK-" + String.format("%03d", i),
                    genre + " Volume " + i,
                    "An immersive " + genre.toLowerCase() + " journey that explores the depths of " + genre + " storytelling.",
                    "https://images.example.com/books/gen-" + i + ".jpg",
                    new BigDecimal("15.99").add(new BigDecimal(i % 10))
            );
        }).toList();

        productRepository.saveAll(randomBooks);
        System.out.println(">> Catalog Seeding Complete: 40 books are ready for sale.");
    }
}
