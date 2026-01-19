package com.montenegro.technova.dto;

import com.montenegro.technova.model.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

  private Long id;
  private String name;
  private String brand;
  private String model;
  private Category category;
  private double price;
  private int stock;
  private String description;
  private int releaseYear;
  private String imageUrl;
  private String createdAt;
  private String updatedAt;

}
