package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BookListResponse {

    private final Long bookId;

    private final String title;

    private final String author;

    private final String translator;

    private final Integer quantity;

    private final Integer price;

    private final String info;

    private final String thumbnailUrl;

    private final List<BookTagResponse> tags;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static BookListResponse toDto(Book book, List<BookTagResponse> tagResponses) {
        return BookListResponse.builder()
            .bookId(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .translator(book.getTranslator())
            .quantity(book.getQuantity())
            .price(book.getPrice())
            .info(book.getInfo())
            .thumbnailUrl(book.getThumbnailUrl())
            .tags(tagResponses)
            .createdAt(book.getCreatedAt())
            .updatedAt(book.getUpdatedAt())
            .build();
    }

    @Builder
    private BookListResponse(
        Long bookId,
        String title,
        String author,
        String translator,
        Integer quantity,
        Integer price,
        String info,
        String thumbnailUrl,
        List<BookTagResponse> tags,
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
