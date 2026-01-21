package com.montenegro.technova.service;

import com.montenegro.technova.dto.ProductRequestDto;
import com.montenegro.technova.dto.ProductResponseDto;
import com.montenegro.technova.model.Category;

import java.util.List;

public interface ProductService {

  ProductResponseDto createProduct(ProductRequestDto dto);

  ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

  void deleteProduct(Long id);

  ProductResponseDto getProductById(Long id);

  List<ProductResponseDto> getAllProducts();

  List<ProductResponseDto> getProductsByCategory(Category category);

  List<ProductResponseDto> searchProductsByName(String name);

  // MÉTRICAS PARA EL DASHBOARD
  long getTotalProducts();

  int getTotalStock();

  long getTotalCategories();
}

