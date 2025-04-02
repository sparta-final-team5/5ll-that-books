package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailRepository extends JpaRepository<BookDetail,Long> {
}
