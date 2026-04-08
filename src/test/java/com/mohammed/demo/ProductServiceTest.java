package com.mohammed.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// @ExtendWith - بيقول لـ JUnit يستخدم Mockito
// زي ما بتعمل في xUnit مع Moq
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    // @Mock - بيعمل Mock للـ Repository
    // زي Mock<IProductRepository> في .NET
    @Mock
    private ProductRepository repository;

    // @InjectMocks - بيعمل Instance من الـ Service ويحقن فيه الـ Mocks
    // زي ما بتعمل new ProductService(mockRepository) في .NET
    @InjectMocks
    private ProductService service;

    @Test
    @DisplayName("getAll should return list of products")
    void getAll_ShouldReturnListOfProducts() {
        // Arrange - زي Arrange في xUnit
        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99),
                new Product("Phone", 599.99)
        );
        when(repository.findAll()).thenReturn(products);

        // Act
        List<Product> result = service.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("getById should return product when found")
    void getById_ShouldReturnProduct_WhenFound() {
        // Arrange
        Product product = new Product("Laptop", 999.99);
        when(repository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Product result = service.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(999.99, result.getPrice());
    }

    @Test
    @DisplayName("getById should throw exception when not found")
    void getById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.getById(999));
    }

    @Test
    @DisplayName("create should save and return product")
    void create_ShouldSaveAndReturnProduct() {
        // Arrange
        Product product = new Product("Laptop", 999.99);
        when(repository.save(product)).thenReturn(product);

        // Act
        Product result = service.create(product);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(repository, times(1)).save(product);
    }

    @Test
    @DisplayName("delete should call deleteById")
    void delete_ShouldCallDeleteById() {
        // Act
        service.delete(1);

        // Assert
        verify(repository, times(1)).deleteById(1);
    }
}