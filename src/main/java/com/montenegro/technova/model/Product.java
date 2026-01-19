package com.montenegro.technova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Product name is required")
  @Column(nullable = false, length = 150)
  private String name;

  @NotBlank(message = "Brand is required")
  @Column(nullable = false, length = 100)
  private String brand;

  @NotBlank(message = "Model is required")
  @Column(nullable = false, length = 100)
  private String model;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 50)
  private Category category;

  @Positive(message = "Price must be greater than zero")
  @Column(nullable = false)
  private double price;

  @Min(value = 0, message = "Stock cannot be negative")
  @Column(nullable = false)
  private int stock;

  @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
  @Column(columnDefinition = "TEXT")
  private String description;

  @Min(value = 2000, message = "Release year must be realistic")
  private int releaseYear;

  @Column(length = 255)
  private String imageFileName;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
