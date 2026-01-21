package com.montenegro.technova.controller;

import com.montenegro.technova.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

  private final ProductService productService;

  @GetMapping("/")
  public String home(Model model) {
    loadDashboardData(model);
    return "dashboard";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    loadDashboardData(model);
    return "dashboard";
  }

  private void loadDashboardData(Model model) {
    model.addAttribute("totalProducts", productService.getTotalProducts());
    model.addAttribute("totalStock", productService.getTotalStock());
    model.addAttribute("totalCategories", productService.getTotalCategories());
    model.addAttribute("activePage", "dashboard");
  }
}
