package com.example.allthatbooks.domain.book.repository;

import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.repository.querydsl.BookRepositoryQueryDsl;
import com.example.allthatbooks.domain.common.exception.CustomException;
import com.example.allthatbooks.domain.common.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryQueryDsl {

    Optional<Book> findBookById(Long bookId);   // JPQL 써야할지도?

    default Book findBookByIdOrElseThrow(Long bookId) {
        return findBookById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOOK));
    }

}
