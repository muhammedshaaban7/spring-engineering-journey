package com.mohammed.demo.dtos;

import java.io.Serializable;

// DTO - بيمثل الـ Response اللي بيتبعت للـ Client
public class ProductDto implements Serializable {

    private int id;
    private String name;
    private double price;
    private String categoryName; // بس اسم الـ Category مش الـ Object كله

    public ProductDto() {}

    public ProductDto(int id, String name, double price, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategoryName() { return categoryName; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}