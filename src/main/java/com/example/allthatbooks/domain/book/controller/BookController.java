package com.example.allthatbooks.domain.book.controller;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
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

    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBook(
        @PathVariable("bookId") Long bookId,
        @RequestBody UpdateBookRequest request
    ) {
        bookService.updateBook(bookId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId    ) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

}
