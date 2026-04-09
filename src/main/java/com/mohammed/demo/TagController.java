package com.mohammed.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagRepository repository;

    public TagController(TagRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Tag create(@Valid @RequestBody Tag tag) {
        return repository.save(tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}