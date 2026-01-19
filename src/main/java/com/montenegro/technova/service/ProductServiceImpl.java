package com.montenegro.technova.service;

import com.montenegro.technova.dto.ProductRequestDto;
import com.montenegro.technova.dto.ProductResponseDto;
import com.montenegro.technova.exeption.ResourceNotFoundException;
import com.montenegro.technova.model.Category;
import com.montenegro.technova.model.Product;
import com.montenegro.technova.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  @Override
  public ProductResponseDto createProduct(ProductRequestDto dto) {
    // Aquí luego usaremos el mapper
    Product product = new Product();
    product.setName(dto.getName());
    product.setBrand(dto.getBrand());
    product.setModel(dto.getName());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setDescription(dto.getDescription());
    product.setReleaseYear(dto.getReleaseYear());
    Product saved = productRepository.save(product);

    return ProductResponseDto.builder()
       .id(saved.getId())
       .name(saved.getName())
       .brand(saved.getBrand())
       .model(saved.getModel())
       .category(saved.getCategory())
       .price(saved.getPrice())
       .stock(saved.getStock())
       .description(saved.getDescription())
       .releaseYear(saved.getReleaseYear())
       .build();


  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
    Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    product.setName(dto.getName());
    product.setBrand(dto.getBrand());
    product.setModel(dto.getModel());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setDescription(dto.getDescription());
    product.setReleaseYear(dto.getReleaseYear());

    Product updated = productRepository.save(product);

    return ProductResponseDto.builder()
       .id(updated.getId())
       .name(updated.getName())
       .brand(updated.getBrand())
       .model(updated.getModel())
       .category(updated.getCategory())
       .price(updated.getPrice())
       .stock(updated.getStock())
       .description(updated.getDescription())
       .releaseYear(updated.getReleaseYear())
       .build();
  }

  @Override
  public void deleteProduct(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product not found");
    }
    productRepository.deleteById(id);
  }

  @Override
  public ProductResponseDto getProductById(Long id) {
    Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

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
       .build();
  }

  @Override
  public List<ProductResponseDto> getAllProducts() {

    return productRepository.findAll()
       .stream()
       .map(product -> ProductResponseDto.builder()
          .id(product.getId())
          .name(product.getName())
          .brand(product.getBrand())
          .model(product.getModel())
          .category(product.getCategory())
          .price(product.getPrice())
          .stock(product.getStock())
          .description(product.getDescription())
          .releaseYear(product.getReleaseYear())
          .build())
       .toList();
  }

  @Override
  public List<ProductResponseDto> getProductsByCategory(Category category) {
    return productRepository.findByCategory(category)
       .stream()
       .map(product -> ProductResponseDto.builder()
          .id(product.getId())
          .name(product.getName())
          .brand(product.getBrand())
          .model(product.getModel())
          .category(product.getCategory())
          .price(product.getPrice())
          .stock(product.getStock())
          .description(product.getDescription())
          .releaseYear(product.getReleaseYear())
          .build())
       .toList();
  }

  @Override
  public List<ProductResponseDto> searchProductsByName(String name) {
    return productRepository.findByNameContainingIgnoreCase(name)
       .stream()
       .map(product -> ProductResponseDto.builder()
          .id(product.getId())
          .name(product.getName())
          .brand(product.getBrand())
          .model(product.getModel())
          .category(product.getCategory())
          .price(product.getPrice())
          .stock(product.getStock())
          .description(product.getDescription())
          .releaseYear(product.getReleaseYear())
          .build())
       .toList();
  }
}
