package com.FindMyPc.back.controller;

import com.FindMyPc.back.ResponseDto.ProductResponseDto;
import com.FindMyPc.back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id) {
        Optional<ProductResponseDto> product = productService.getProductById(id);
        return product.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductResponseDto productResponseDto) {
        ProductResponseDto savedProduct = productService.saveProduct(productResponseDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable int id, @RequestBody ProductResponseDto productResponseDto) {
        productResponseDto.setProductID(id);
        ProductResponseDto updatedProduct = productService.updateProduct(productResponseDto);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ProductResponseDto>> getProductsPaginated(@RequestParam int page, @RequestParam int size) {
        Page<ProductResponseDto> productPage = productService.getProductsPaginated(page, size);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
