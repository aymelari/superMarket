package org.example.supermarket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "brand", "productionDate"}))
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private LocalDate productionDate;
    private Double price;
    private LocalDateTime lastModifiedDate;
    private Boolean expired;
    private Integer stock;


}
