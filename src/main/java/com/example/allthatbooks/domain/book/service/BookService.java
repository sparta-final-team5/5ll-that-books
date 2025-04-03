package com.example.allthatbooks.domain.book.service;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
import com.example.allthatbooks.domain.book.dto.response.BookDetailResponse;
import com.example.allthatbooks.domain.book.dto.response.BookSingleResponse;
import com.example.allthatbooks.domain.book.dto.response.BookTagResponse;
import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.entity.BookDetail;
import com.example.allthatbooks.domain.book.entity.BookTag;
import com.example.allthatbooks.domain.book.repository.BookDetailRepository;
import com.example.allthatbooks.domain.book.repository.BookRepository;
import com.example.allthatbooks.domain.book.repository.BookTagRepository;
import com.example.allthatbooks.domain.common.dto.ImageUrl;
import com.example.allthatbooks.domain.common.enums.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookTagRepository bookTagRepository;
    private final BookDetailRepository bookDetailRepository;

    @Transactional
    public BookSingleResponse createBook(CreateBookRequest request) {
        Book book = Book.createBook(request);

        for (Tag tag : request.getTags()) {
            BookTag bookTag = BookTag.createBookTag(tag);
            book.addTag(bookTag);
        }

        for (ImageUrl image : request.getImages()) {
            BookDetail bookDetail = BookDetail.createBookDetail(image.getImageUrl());
            book.addDetail(bookDetail);
        }

        Book savedBook = bookRepository.save(book);
        List<BookTagResponse> tagResponse = savedBook.getBookTagList()
            .stream()
            .map(BookTagResponse::toDto)
            .toList();
        List<BookDetailResponse> detailResponse = savedBook.getBookDetailList()
            .stream()
            .map(BookDetailResponse::toDto)
            .toList();

        return BookSingleResponse.toDto(savedBook, tagResponse, detailResponse);
    }

    @Transactional
    public void updateBook(Long bookId, UpdateBookRequest request) {
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);

        //TODO: userId 검증 로직 필요
        foundBook.updateBook(request);

    }

    @Transactional
    public void deleteBook(Long bookId) {
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);
        foundBook.deleteBook(); // 일단 soft
    }

}
