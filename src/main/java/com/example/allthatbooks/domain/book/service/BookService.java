package com.example.allthatbooks.domain.book.service;

import com.example.allthatbooks.common.dto.ImageUrl;
import com.example.allthatbooks.common.dto.response.PageResponse;
import com.example.allthatbooks.common.enums.Tag;
import com.example.allthatbooks.common.exception.CustomException;
import com.example.allthatbooks.common.exception.ErrorCode;
import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateDetailImageRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateTagRequest;
import com.example.allthatbooks.domain.book.dto.response.BookListResponse;
import com.example.allthatbooks.domain.book.dto.response.BookSingleResponse;
import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.entity.BookDetailImage;
import com.example.allthatbooks.domain.book.entity.BookTag;
import com.example.allthatbooks.domain.book.repository.BookDetailRepository;
import com.example.allthatbooks.domain.book.repository.BookRepository;
import com.example.allthatbooks.domain.book.repository.BookTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
            BookTag bookTag = BookTag.createBookTag(tag, book);
            book.addTag(bookTag);
        }

        for (ImageUrl image : request.getImages()) {
            BookDetailImage bookDetailImage = BookDetailImage.createBookDetail(image.getImageUrl(), book);
            book.addDetail(bookDetailImage);
        }

        Book savedBook = bookRepository.save(book);
        return BookSingleResponse.toDto(savedBook);
    }

    public PageResponse<BookListResponse> findAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(Math.max(0, page - 1), size);
        Page<BookListResponse> results = bookRepository.findAllPaging(pageable, search);
        return PageResponse.toDto(results);
    }

    public BookSingleResponse findOne(Long bookId) {
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);

        if (foundBook.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_BOOK);
        }

        return BookSingleResponse.toDto(foundBook);
    }

    @Transactional
    public void updateBook(Long bookId, UpdateBookRequest request) {
        //TODO: userId 검증 로직 필요
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);

        if (foundBook.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_BOOK);
        }

        foundBook.updateBasicInfo(request);
    }

    @Transactional
    public void updateTags(Long bookId, UpdateTagRequest request) {
        //TODO: userId 검증 로직 필요
        Book foundBook = bookRepository.findBookByIdWithTagsOrElseThrow(bookId);

        if (foundBook.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_BOOK);
        }

        foundBook.updateTags(request);
    }

    @Transactional
    public void updateDetailImages(Long bookId, UpdateDetailImageRequest request) {
        //TODO: userId 검증 로직 필요
        Book foundBook = bookRepository.findBookByIdWithDetailImagesOrElseThrow(bookId);

        if (foundBook.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_BOOK);
        }

        foundBook.updateDetailImages(request);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        //TODO: userId 검증 로직 필요
        Book foundBook = bookRepository.findBookByIdOrElseThrow(bookId);

        if (foundBook.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_BOOK);
        }

        foundBook.deleteBook();
    }
}
