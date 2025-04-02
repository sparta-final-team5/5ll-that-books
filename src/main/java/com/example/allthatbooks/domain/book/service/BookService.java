package com.example.allthatbooks.domain.book.service;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long createBook(CreateBookRequest request) {
        Book book = Book.createBook(request);
        Book saveBook = bookRepository.save(book);
        return saveBook.getId();
    }


}
