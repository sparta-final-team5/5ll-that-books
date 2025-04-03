package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Getter
public class BookSingleResponse {

    private Long bookId;

    private String title;

    private String author;

    private String translator;

    private Integer quantity;

    private Integer price;

    private String info;

    private String thumbnailUrl;

    private List<BookTagResponse> tags;

    private List<BookDetailResponse> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static BookSingleResponse toDto(Book book, List<BookTagResponse> tagResponses, List<BookDetailResponse> detailResponses) {
        return BookSingleResponse.builder()
            .bookId(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .translator(book.getTranslator())
            .quantity(book.getQuantity())
            .price(book.getPrice())
            .info(book.getInfo())
            .thumbnailUrl(book.getThumbnailUrl())
            .tags(tagResponses)
            .images(detailResponses)
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
        List<BookDetailResponse> images,
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
