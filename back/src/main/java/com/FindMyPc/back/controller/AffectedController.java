package com.FindMyPc.back.controller;


import com.FindMyPc.back.entity.Affected;
import com.FindMyPc.back.service.AffectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/affected")
public class AffectedController {

    @Autowired
    private AffectedService affectedService;

    @GetMapping
    public List<Affected> getAllAffected() {
        return affectedService.getAllAffected();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Affected> getAffectedById(@PathVariable int id) {
        Optional<Affected> affected = affectedService.getAffectedById(id);
        return affected.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Affected createAffected(@RequestBody Affected affected) {
        return affectedService.saveAffected(affected);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Affected> updateAffected(@PathVariable int id, @RequestBody Affected affectedDetails) {
        Optional<Affected> optionalAffected = affectedService.getAffectedById(id);

        if (optionalAffected.isPresent()) {
            Affected affected = optionalAffected.get();
            affected.setPriceBD(affectedDetails.getPriceBD());
            affected.setPriceAD(affectedDetails.getPriceAD());
            affected.setProduct(affectedDetails.getProduct());
            affected.setStore(affectedDetails.getStore());
            return ResponseEntity.ok(affectedService.saveAffected(affected));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffected(@PathVariable int id) {
        Optional<Affected> affected = affectedService.getAffectedById(id);

        if (affected.isPresent()) {
            affectedService.deleteAffected(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginated")
    public Page<Affected> getAffectedPaginated(@RequestParam int page, @RequestParam int size) {
        return affectedService.getAffectedPaginated(page, size);
    }
}
