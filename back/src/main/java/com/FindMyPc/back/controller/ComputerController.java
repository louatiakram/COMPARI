package tech.louatiakram.scrapping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.louatiakram.scrapping.entities.Computer;
import tech.louatiakram.scrapping.services.ComputerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping
    public List<Computer> getAllProducts() {
        return computerService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getProductById(@PathVariable Long id) {
        Optional<Computer> product = computerService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Computer createProduct(@RequestBody Computer computer) {
        return computerService.saveProduct(computer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Computer> updateProduct(@PathVariable Long id, @RequestBody Computer updatedComputer) {
        Optional<Computer> existingProduct = computerService.getProductById(id);
        if (existingProduct.isPresent()) {
            updatedComputer.setId(id);
            return ResponseEntity.ok(computerService.saveProduct(updatedComputer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return computerService.deleteProduct(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
