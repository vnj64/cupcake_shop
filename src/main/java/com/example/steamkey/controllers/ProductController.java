package com.example.steamkey.controllers;

import com.example.steamkey.common.ResourceNotFoundException;
import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.repositories.ProductRepository;
import com.example.steamkey.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(value = "/", produces = {"application/json", "text/xml"})
    public String products(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "products";
    }

    @GetMapping(value = "/product/{id}", produces = {"application/json", "text/xml"})
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        long count = productRepository.count();

        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("recordCount", count);
        model.addAttribute("authorProduct", product.getUser());
        return "product-info";
    }

    @PostMapping(value = "/product/create", produces = {"application/json", "text/xml"})
    public String createProduct(Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product);
        return "redirect:/my/products";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
        return "redirect:/my/products";
    }

    @GetMapping(value = "/my/products", produces = {"application/json", "text/xml"})
    public String userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }
}
