package com.mohammed.demo.mappers;

import com.mohammed.demo.Category;
import com.mohammed.demo.Product;
import com.mohammed.demo.Tag;
import com.mohammed.demo.dtos.ProductDto;
import com.mohammed.demo.dtos.ProductRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        String categoryName = product.getCategory() != null
                ? product.getCategory().getName()
                : null;

        Set<String> tags = product.getTags() != null
                ? product.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toSet())
                : null;

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryName,
                tags
        );
    }

    public Product toEntity(ProductRequest request, Category category, Set<Tag> tags) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setTags(tags);
        return product;
    }
}