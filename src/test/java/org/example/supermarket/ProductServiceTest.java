package org.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_ShouldSaveAndReturnId() {
        ProductRequestDto productRequestDto = new ProductRequestDto("Test Product", 100.0, 10);
        Product product = new Product();
        product.setId(1L);

        when(modelMapper.map(productRequestDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Long productId = productService.createProduct(productRequestDto);

        assertNotNull(productId);
        assertEquals(1L, productId);
        verify(productRepository).save(product);
    }

    @Test
    void discount10_ShouldApplyDiscountToProductsModifiedMoreThan5DaysAgo() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(5);
        Product product1 = new Product(1L, "Product 1", 100.0, 10, thresholdDate.minusDays(1));
        Product product2 = new Product(2L, "Product 2", 200.0, 5, thresholdDate.minusDays(2));

        // Mock repository to return products
        when(productRepository.findByLastModifiedDate(any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(product1, product2));
        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0)); // Return updated product

        // Call the method under test
        productService.discount10();

        // Verify price updates
        assertEquals(90.0, product1.getPrice(), 0.01);
        assertEquals(180.0, product2.getPrice(), 0.01);

        // Verify repository interactions
        verify(productRepository, times(1)).findByLastModifiedDate(any(LocalDateTime.class));
        verify(productRepository, times(1)).save(product1);
        verify(productRepository, times(1)).save(product2);
    }





    @Test
    void lastModified1month_ShouldMarkOldProductsAsExpired() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        Product product1 = new Product(1L, "Product 1", 100.0, 10, oneMonthAgo.minusDays(1), false);
        Product product2 = new Product(2L, "Product 2", 200.0, 5, oneMonthAgo.minusDays(2), false);

        // Mock repository to return products
        when(productRepository.findByLastModifiedDateeBeforeAndExpiredFalse(any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(product1, product2));

        // Mock the save() method
        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0)); // Return updated product

        // Call the method under test
        productService.lastModified1month();

        // Verify the expired field is updated
        assertTrue(product1.getExpired());
        assertTrue(product2.getExpired());

        // Verify repository interactions
        verify(productRepository, times(1)).findByLastModifiedDateeBeforeAndExpiredFalse(any(LocalDateTime.class));
        verify(productRepository, times(1)).save(product1);
        verify(productRepository, times(1)).save(product2);
    }


    @Test
    void totalCost_ShouldReturnTotalCostOfAvailableProducts() {
        Product product1 = new Product(1L, "Product 1", 100.0, 10, LocalDateTime.now());
        Product product2 = new Product(2L, "Product 2", 200.0, 5, LocalDateTime.now());

        when(productRepository.findAvailableProducts()).thenReturn(Arrays.asList(product1, product2));

        Double totalCost = productService.TotalCost();

        assertEquals(2000.0, totalCost);
        verify(productRepository).findAvailableProducts();
    }

    @Test
    void totalCost_ShouldThrowException_WhenNoProductsAvailable() {
        when(productRepository.findAvailableProducts()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.TotalCost());

        assertEquals("No products available", exception.getMessage());
        verify(productRepository).findAvailableProducts();
    }
}
