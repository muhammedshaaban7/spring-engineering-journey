package com.mohammed.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    // جيب Tags بقائمة من الـ IDs
    Set<Tag> findByIdIn(Set<Integer> ids);
}