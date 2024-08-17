package com.FindMyPc.back.controller;

import com.FindMyPc.back.ResponseDto.ProductsResponseDto;
import com.FindMyPc.back.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<ProductsResponseDto>> getAllProducts() {
        List<ProductsResponseDto> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponseDto> getProductById(@PathVariable int id) {
        Optional<ProductsResponseDto> product = productsService.getProductById(id);
        return product.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductsResponseDto> createProduct(@RequestBody ProductsResponseDto productsResponseDto) {
        ProductsResponseDto savedProduct = productsService.saveProduct(productsResponseDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsResponseDto> updateProduct(@PathVariable long id, @RequestBody ProductsResponseDto productsResponseDto) {
        productsResponseDto.setId(id);
        ProductsResponseDto updatedProduct = productsService.updateProduct(productsResponseDto);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ProductsResponseDto>> getProductsPaginated(@RequestParam int page, @RequestParam int size) {
        Page<ProductsResponseDto> productPage = productsService.getProductsPaginated(page, size);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
