package com.mohammed.demo;

import com.mohammed.demo.dtos.ProductDto;
import com.mohammed.demo.dtos.ProductRequest;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/paged")
    public Page<ProductDto> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return service.getAllPaged(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PostMapping
    public ProductDto create(@Valid @RequestBody ProductRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") int id,
                             @Valid @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}