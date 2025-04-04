package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {

}
