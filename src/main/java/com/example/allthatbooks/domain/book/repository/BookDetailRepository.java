package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.BookDetailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailRepository extends JpaRepository<BookDetailImage,Long> {

}
