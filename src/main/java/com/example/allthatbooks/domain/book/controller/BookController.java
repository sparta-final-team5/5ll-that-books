package com.example.allthatbooks.domain.book.controller;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody CreateBookRequest request) {
        bookService.createBook(request);
        return ResponseEntity.ok().build();
    }
}
