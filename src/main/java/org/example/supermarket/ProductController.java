package org.example.supermarket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/api/products/total-cost")
    public ResponseEntity<Double> totalCost() {
        return ResponseEntity.ok(service.TotalCost());
    }


    @PostMapping("/api/products")
    public ResponseEntity<Long> newProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(service.createProduct(productRequestDto));
    }
}
