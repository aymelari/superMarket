package org.example.supermarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequestDto {
    private String name;
    private String brand;
    private LocalDate productionDate;
    private Double price;
    private LocalDateTime lastModifiedDate;
    private Boolean expired;
    private Integer stock;

    public ProductRequestDto(String testProduct, double v, int i) {
    }
}
