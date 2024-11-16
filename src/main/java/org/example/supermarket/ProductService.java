package org.example.supermarket;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Long createProduct(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        productRepository.save(product);
        return product.getId();

    }


    @Scheduled(fixedDelay = 86400000)
    public void discount10() {

        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(5);
        List<Product> byLastModifiedDate = productRepository.findByLastModifiedDate(thresholdDate);
        byLastModifiedDate.forEach(n -> n.setPrice(n.getPrice() * 0.9));
    }

    @Scheduled(fixedDelay = 86400000)
    public void lastModified1month() {
        LocalDateTime callculated = LocalDateTime.now().minusMonths(1);
        List<Product> byLastModified = productRepository.findByLastModifiedDateeBeforeAndExpiredFalse(callculated);
        byLastModified.forEach(n -> n.setExpired(Boolean.FALSE));
    }

    public Double TotalCost() {
        List<Product> availableProducts = productRepository.findAvailableProducts();
        if (availableProducts.isEmpty()) {
            throw new RuntimeException("No products available");
        }
        Double totalCost = 0.0;
        for (Product product : availableProducts) {
            totalCost = totalCost + product.getPrice()*product.getStock();

        }
        return totalCost;

    }

}
