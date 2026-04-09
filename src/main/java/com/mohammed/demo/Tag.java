package com.mohammed.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Tag name is required")
    @Column(nullable = false, unique = true)
    private String name;

    // Many Tags belong to Many Products
    @ManyToMany(mappedBy = "tags")
    private Set<Product> products = new HashSet<>();

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Set<Product> getProducts() { return products; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProducts(Set<Product> products) { this.products = products; }
}