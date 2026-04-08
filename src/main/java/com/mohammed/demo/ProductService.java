package com.mohammed.demo;

import com.mohammed.demo.dtos.ProductDto;
import com.mohammed.demo.dtos.ProductRequest;
import com.mohammed.demo.mappers.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class ProductService {

    // Logger - زي ILogger في .NET
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository,
                          ProductMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    // @Cacheable - لو البيانات موجودة في Cache مش هيروح للـ Database
    // زي IMemoryCache.GetOrCreate في .NET
    @Cacheable("products")
    public List<ProductDto> getAll() {
        log.info("Fetching all products from database");
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "products", key = "#id")
    public ProductDto getById(int id) {
        log.info("Fetching product with id: {}", id);
        Product product = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new ResourceNotFoundException("Product not found with id: " + id);
                });
        return mapper.toDto(product);
    }

    // @CacheEvict - بيمسح الـ Cache لما البيانات بتتغير
    // زي IMemoryCache.Remove في .NET
    @CacheEvict(value = "products", allEntries = true)
    public ProductDto create(ProductRequest request) {
        log.info("Creating product with name: {}", request.getName());
        Category category = null;
        if (request.getCategoryId() != 0) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        }
        Product product = mapper.toEntity(request, category);
        ProductDto saved = mapper.toDto(repository.save(product));
        log.info("Product created successfully with id: {}", saved.getId());
        return saved;
    }

    @CacheEvict(value = "products", allEntries = true)
    public ProductDto update(int id, ProductRequest request) {
        log.info("Updating product with id: {}", id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        if (request.getCategoryId() != 0) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
            product.setCategory(category);
        }

        ProductDto updated = mapper.toDto(repository.save(product));
        log.info("Product updated successfully with id: {}", id);
        return updated;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void delete(int id) {
        log.info("Deleting product with id: {}", id);
        repository.deleteById(id);
        log.info("Product deleted successfully with id: {}", id);
    }

    public Page<ProductDto> getAllPaged(int page, int size, String sortBy) {
        log.info("Fetching products - page: {}, size: {}, sortBy: {}", page, size, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repository.findAll(pageable).map(mapper::toDto);
    }
}