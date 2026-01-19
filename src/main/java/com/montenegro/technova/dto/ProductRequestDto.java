package com.montenegro.technova.dto;

import com.montenegro.technova.model.Category;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

  @NotBlank(message = "Product name is required")
  private String name;

  @NotBlank(message = "Brand is required")
  private String brand;

  @NotBlank(message = "Model is required")
  private String model;

  @NotNull(message = "Category is required")
  private Category category;

  @Positive(message = "Price must be greater than zero")
  private double price;

  @Min(value = 0, message = "Stock cannot be negative")
  private int stock;

  @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
  private String description;

  @Min(value = 2000, message = "Release year must be realistic")
  private int releaseYear;

  private MultipartFile imageFile;

}
