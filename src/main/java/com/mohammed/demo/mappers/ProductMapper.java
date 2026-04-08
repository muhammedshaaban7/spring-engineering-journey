package com.mohammed.demo.mappers;

import com.mohammed.demo.Product;
import com.mohammed.demo.Category;
import com.mohammed.demo.dtos.ProductDto;
import com.mohammed.demo.dtos.ProductRequest;
import org.springframework.stereotype.Component;

// @Component - عشان Spring يعرفه ويعمله Inject
// زي AutoMapper في .NET
@Component
public class ProductMapper {

    // من Entity لـ DTO - بيتبعت للـ Client
    public ProductDto toDto(Product product) {
        String categoryName = product.getCategory() != null
                ? product.getCategory().getName()
                : null;

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryName
        );
    }

    // من DTO لـ Entity - بيتحفظ في الـ Database
    public Product toEntity(ProductRequest request, Category category) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        return product;
    }
}