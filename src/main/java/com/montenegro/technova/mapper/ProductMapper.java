package com.montenegro.technova.mapper;

import com.montenegro.technova.dto.ProductRequestDto;
import com.montenegro.technova.dto.ProductResponseDto;
import com.montenegro.technova.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product toEntity(ProductRequestDto dto) {
    if (dto == null) return null;

    return Product.builder()
       .name(dto.getName())
       .brand(dto.getBrand())
       .model(dto.getModel())
       .category(dto.getCategory())
       .price(dto.getPrice())
       .stock(dto.getStock())
       .description(dto.getDescription())
       .releaseYear(dto.getReleaseYear())
       .build();
  }

  public void updateEntity(Product product, ProductRequestDto dto) {
    product.setName(dto.getName());
    product.setBrand(dto.getBrand());
    product.setModel(dto.getModel());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setDescription(dto.getDescription());
    product.setReleaseYear(dto.getReleaseYear());
  }

  public ProductResponseDto toResponse(Product product) {
    if (product == null) return null;

    return ProductResponseDto.builder()
       .id(product.getId())
       .name(product.getName())
       .brand(product.getBrand())
       .model(product.getModel())
       .category(product.getCategory())
       .price(product.getPrice())
       .stock(product.getStock())
       .description(product.getDescription())
       .releaseYear(product.getReleaseYear())
       .imageUrl(product.getImageFileName())
       .createdAt(product.getCreatedAt()
          .toString())
       .updatedAt(product.getUpdatedAt()
          .toString())
       .build();
  }
}
