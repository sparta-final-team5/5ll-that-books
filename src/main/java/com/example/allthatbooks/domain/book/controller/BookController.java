package com.example.allthatbooks.domain.book.controller;

import com.example.allthatbooks.common.dto.response.PageResponse;
import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateDetailImageRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateTagRequest;
import com.example.allthatbooks.domain.book.dto.response.BookListResponse;
import com.example.allthatbooks.domain.book.dto.response.BookSingleResponse;
import com.example.allthatbooks.domain.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookSingleResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        BookSingleResponse response = bookService.createBook(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookListResponse>> findAll(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "search", required = false) String search
    ) {
        PageResponse<BookListResponse> responses = bookService.findAll(page, size, search);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookSingleResponse> findOne(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(bookService.findOne(bookId));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBook(
        @PathVariable("bookId") Long bookId,
        @Valid @RequestBody UpdateBookRequest request
    ) {
        bookService.updateBook(bookId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookId}/tags")
    public ResponseEntity<Void> updateTags(
        @PathVariable("bookId") Long bookId,
        @Valid @RequestBody UpdateTagRequest request
    ) {
        bookService.updateTags(bookId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookId}/images")
    public ResponseEntity<Void> updateDetailImages(
        @PathVariable("bookId") Long bookId,
        @Valid @RequestBody UpdateDetailImageRequest request
    ) {
        bookService.updateDetailImages(bookId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

}
