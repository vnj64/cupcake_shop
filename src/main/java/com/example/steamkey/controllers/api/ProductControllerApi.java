package com.example.steamkey.controllers.api;

import com.example.steamkey.common.ProductDTO;
import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerApi {
    private final ProductService productService;

    @GetMapping("/api/v1/products/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setTitle(product.getTitle());
            productDTO.setDescription(product.getDescription());
            productDTO.setKey(product.getKey());
            productDTO.setArea(product.getArea());
            productDTO.setPrice(product.getPrice());

            productDTOs.add(productDTO);
        }
        if (!productDTOs.isEmpty()) {
            return new ResponseEntity<>(productDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/api/v1/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> productInfoApi(@PathVariable Long id, Principal principal) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setArea(product.getArea());
        productDTO.setKey(product.getKey());
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/api/v1/product/create")
    public ResponseEntity<Void> createProductApi(@RequestBody Product product, Principal principal) {
        try {
            productService.saveProduct(principal, product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/v1/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
    }

    @GetMapping("/api/v1/my/products")
    public List<Product> userProductsApi(Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        return user.getProducts();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the product.");
    }
}
