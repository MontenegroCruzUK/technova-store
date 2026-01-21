package com.montenegro.technova.controller;

import com.montenegro.technova.dto.ProductRequestDto;
import com.montenegro.technova.model.Category;
import com.montenegro.technova.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  // LIST PRODUCTS
  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("activePage", "products");
    return "products/list";
  }

  // SHOW CREATE FORM
  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("product", new ProductRequestDto());
    model.addAttribute("categories", Category.values());
    model.addAttribute("activePage", "products");
    return "products/create";
  }

  // CREATE PRODUCT
  @PostMapping("/create")
  public String createProduct(
     @Valid @ModelAttribute("product") ProductRequestDto dto,
     BindingResult result,
     @RequestParam("imageFile") MultipartFile imageFile,
     Model model
  ) {
    if (result.hasErrors()) {
      model.addAttribute("categories", Category.values());
      model.addAttribute("activePage", "products");
      return "products/create";
    }

    dto.setImageFile(imageFile);
    productService.createProduct(dto);
    return "redirect:/products";
  }

  // SHOW EDIT FORM
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    var product = productService.getProductById(id);

    ProductRequestDto dto = ProductRequestDto.builder()
       .name(product.getName())
       .brand(product.getBrand())
       .model(product.getModel())
       .category(product.getCategory())
       .price(product.getPrice())
       .stock(product.getStock())
       .description(product.getDescription())
       .releaseYear(product.getReleaseYear())
       .build();

    model.addAttribute("product", dto);
    model.addAttribute("productId", id);
    model.addAttribute("categories", Category.values());
    model.addAttribute("activePage", "products");
    return "products/edit";
  }

  // UPDATE PRODUCT
  @PostMapping("/edit/{id}")
  public String updateProduct(
     @PathVariable Long id,
     @Valid @ModelAttribute("product") ProductRequestDto dto,
     BindingResult result,
     @RequestParam("imageFile") MultipartFile imageFile,
     Model model
  ) {
    if (result.hasErrors()) {
      model.addAttribute("categories", Category.values());
      model.addAttribute("activePage", "products");
      return "products/edit";
    }

    dto.setImageFile(imageFile);
    productService.updateProduct(id, dto);
    return "redirect:/products";
  }

  // DELETE PRODUCT
  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/products";
  }
}
