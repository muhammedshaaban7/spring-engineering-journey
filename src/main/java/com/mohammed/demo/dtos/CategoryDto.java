package com.mohammed.demo.dtos;

// DTO - بيمثل الـ Response بتاع الـ Category
public class CategoryDto {

    private int id;
    private String name;
    private int productsCount; // عدد المنتجات بس مش قائمتهم كلها

    public CategoryDto() {}

    public CategoryDto(int id, String name, int productsCount) {
        this.id = id;
        this.name = name;
        this.productsCount = productsCount;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getProductsCount() { return productsCount; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProductsCount(int productsCount) { this.productsCount = productsCount; }
}