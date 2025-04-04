package com.example.allthatbooks.domain.book.repository.querydsl;

import com.example.allthatbooks.domain.book.dto.response.BookListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQueryDsl {

    Page<BookListResponse> findAllPaging(Pageable pageable, String search);

}
