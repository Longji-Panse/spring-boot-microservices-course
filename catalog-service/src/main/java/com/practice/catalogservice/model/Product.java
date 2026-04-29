package com.practice.catalogservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product code is required")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    private String imageUrl;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    // Constructors
    public Product() {}

    public Product(Long id, String code, String name, String description, String imageUrl, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public BigDecimal getPrice() { return price; }
}
