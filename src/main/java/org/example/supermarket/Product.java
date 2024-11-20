package org.example.supermarket;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "brand", "productionDate"}))
@Builder
@Setter
@Getter
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




    public Product(Long id, String name, Double price, int stock, LocalDateTime lastModifiedDate) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.lastModifiedDate = lastModifiedDate;
        }

    public Product(long l, String s, double v, int i, LocalDateTime localDateTime, boolean b) {
this.id = l;
this.name = s;
this.price = v;
this.stock = i;
this.lastModifiedDate = localDateTime;
this.expired = b;
    }
}


