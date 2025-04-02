package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.repository.querydsl.BookRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryQueryDsl {

}
