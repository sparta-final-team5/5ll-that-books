package com.example.allthatbooks.domain.book.service;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
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

    @Transactional
    public void updateBook(Long bookId, UpdateBookRequest request) {
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);
        foundBook.updateBook(request);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);
        foundBook.deleteBook(); // 일단 soft
    }



}
