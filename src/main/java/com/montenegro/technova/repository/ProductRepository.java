package com.montenegro.technova.repository;

import com.montenegro.technova.model.Category;
import com.montenegro.technova.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  // Buscar por categoría
  List<Product> findByCategory(Category category);

  // Buscar por nombre (contiene)
  List<Product> findByNameContainingIgnoreCase(String name);

  // Buscar por marca
  List<Product> findByBrandIgnoreCase(String brand);
}
