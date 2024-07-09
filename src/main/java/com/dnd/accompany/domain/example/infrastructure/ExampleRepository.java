package com.dnd.accompany.domain.example.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.accompany.domain.example.entity.Example;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}
