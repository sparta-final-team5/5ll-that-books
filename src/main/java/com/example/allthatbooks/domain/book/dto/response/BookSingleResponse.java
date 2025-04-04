package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Getter
public class BookSingleResponse {

    private final Long bookId;

    private final String title;

    private final String author;

    private final String translator;

    private final Integer quantity;

    private final Integer price;

    private final String info;

    private final String thumbnailUrl;

    private final List<BookTagResponse> tags;

    private final List<BookDetailImageResponse> images;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static BookSingleResponse toDto(Book book) {
        List<BookTagResponse> tagResponse = book.getBookTagSet()
            .stream()
            .map(BookTagResponse::toDto)
            .toList();

        List<BookDetailImageResponse> detailResponse = book.getBookDetailImageList()
            .stream()
            .map(BookDetailImageResponse::toDto)
            .toList();

        return BookSingleResponse.builder()
            .bookId(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .translator(book.getTranslator())
            .quantity(book.getQuantity())
            .price(book.getPrice())
            .info(book.getInfo())
            .thumbnailUrl(book.getThumbnailUrl())
            .tags(tagResponse)
            .images(detailResponse)
            .createdAt(book.getCreatedAt())
            .updatedAt(book.getUpdatedAt())
            .build();
    }

    @Builder
    private BookSingleResponse(
        Long bookId,
        String title,
        String author,
        String translator,
        Integer quantity,
        Integer price,
        String info,
        String thumbnailUrl,
        List<BookTagResponse> tags,
        List<BookDetailImageResponse> images,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.translator = translator;
        this.quantity = quantity;
        this.price = price;
        this.info = info;
        this.thumbnailUrl = thumbnailUrl;
        this.tags = tags;
        this.images = images;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
