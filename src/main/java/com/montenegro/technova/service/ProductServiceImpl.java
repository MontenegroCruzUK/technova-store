package com.montenegro.technova.service;

import com.montenegro.technova.dto.ProductRequestDto;
import com.montenegro.technova.dto.ProductResponseDto;
import com.montenegro.technova.exeption.ResourceNotFoundException;
import com.montenegro.technova.mapper.ProductMapper;
import com.montenegro.technova.model.Category;
import com.montenegro.technova.model.Product;
import com.montenegro.technova.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final FileStorageService fileStorageService;

  @Override
  public ProductResponseDto createProduct(ProductRequestDto dto) {

    Product product = productMapper.toEntity(dto);

    // Guardar imagen si existe
    if (dto.getImageFile() != null && !dto.getImageFile()
       .isEmpty())
    {
      String fileName = fileStorageService.saveFile(dto.getImageFile());
      product.setImageFileName(fileName);
    }
    Product saved = productRepository.save(product);
    return productMapper.toResponse(saved);

  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
    Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    // Actualizar campos
    productMapper.updateEntity(product, dto);

    // Si hay nueva imagen, borrar la anterior y guardar la nueva
    if (dto.getImageFile() != null && !dto.getImageFile()
       .isEmpty())
    {
      // Borrar imagen antigua
      fileStorageService.deleteFile(product.getImageFileName());
      // Guardar nueva imagen
      String fileName = fileStorageService.saveFile(dto.getImageFile());
      product.setImageFileName(fileName);
    }
    Product update = productRepository.save(product);
    return productMapper.toResponse(update);
  }

  @Override
  public void deleteProduct(Long id) {
    Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    // Borrar imagen asociada
    fileStorageService.deleteFile(product.getImageFileName());
    productRepository.delete(product);
  }

  @Override
  public ProductResponseDto getProductById(Long id) {
    Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    return productMapper.toResponse(product);
  }

  @Override
  public List<ProductResponseDto> getAllProducts() {

    return productRepository.findAll()
       .stream()
       .map(productMapper::toResponse)
       .toList();
  }

  @Override
  public List<ProductResponseDto> getProductsByCategory(Category category) {
    return productRepository.findByCategory(category)
       .stream()
       .map(productMapper::toResponse)
       .toList();
  }

  @Override
  public List<ProductResponseDto> searchProductsByName(String name) {
    return productRepository.findByNameContainingIgnoreCase(name)
       .stream()
       .map(productMapper::toResponse)
       .toList();
  }

  @Override
  public long getTotalProducts() {
    return productRepository.count();
  }

  @Override
  public int getTotalStock() {
    return productRepository.findAll()
       .stream()
       .mapToInt(p -> p.getStock())
       .sum();
  }

  @Override
  public long getTotalCategories() {
    return Arrays.stream(Category.values()).count();
  }

}
