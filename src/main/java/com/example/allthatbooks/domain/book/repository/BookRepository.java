package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.repository.querydsl.BookRepositoryQueryDsl;
import com.example.allthatbooks.common.exception.CustomException;
import com.example.allthatbooks.common.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryQueryDsl {

    Optional<Book> findBookById(Long bookId);

    default Book findBookByIdOrElseThrow(Long bookId) {
        return findBookById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOOK));
    }

}
