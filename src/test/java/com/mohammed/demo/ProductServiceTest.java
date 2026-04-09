package com.mohammed.demo;

import com.mohammed.demo.dtos.ProductDto;
import com.mohammed.demo.dtos.ProductRequest;
import com.mohammed.demo.mappers.ProductMapper;
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

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private ProductMapper mapper;

    // @InjectMocks - بيعمل Instance من الـ Service ويحقن فيه الـ Mocks
    // زي ما بتعمل new ProductService(mockRepository) في .NET
    @InjectMocks
    private ProductService service;

    @Test
    @DisplayName("getAll should return list of products")
    void getAll_ShouldReturnListOfProducts() {
        // Arrange - زي Arrange في xUnit
        Product product1 = new Product("Laptop", 999.99);
        Product product2 = new Product("Phone", 599.99);
        List<Product> products = Arrays.asList(product1, product2);
        
        ProductDto dto1 = new ProductDto(1, "Laptop", 999.99, null, null);
        ProductDto dto2 = new ProductDto(2, "Phone", 599.99, null, null);
        
        when(repository.findAll()).thenReturn(products);
        when(mapper.toDto(product1)).thenReturn(dto1);
        when(mapper.toDto(product2)).thenReturn(dto2);

        // Act
        List<ProductDto> result = service.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).toDto(any(Product.class));
    }

    @Test
    @DisplayName("getById should return product when found")
    void getById_ShouldReturnProduct_WhenFound() {
        // Arrange
        Product product = new Product("Laptop", 999.99);
        ProductDto dto = new ProductDto(1, "Laptop", 999.99, null, null);
        
        when(repository.findById(1)).thenReturn(Optional.of(product));
        when(mapper.toDto(product)).thenReturn(dto);

        // Act
        ProductDto result = service.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(999.99, result.getPrice());
        verify(mapper, times(1)).toDto(product);
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
        ProductRequest request = new ProductRequest();
        request.setName("Laptop");
        request.setPrice(999.99);
        request.setCategoryId(0);
        
        Product product = new Product("Laptop", 999.99);
        ProductDto dto = new ProductDto(1, "Laptop", 999.99, null, null);
        
        when(mapper.toEntity(eq(request), eq(null), anySet())).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(mapper.toDto(product)).thenReturn(dto);

        // Act
        ProductDto result = service.create(request);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(repository, times(1)).save(product);
        verify(mapper, times(1)).toEntity(eq(request), eq(null), anySet());
        verify(mapper, times(1)).toDto(product);
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