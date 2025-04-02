package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
