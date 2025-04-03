package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.BookDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDetailResponse {

    private final Long id;
    private final String imageUrl;

    public static BookDetailResponse toDto(BookDetail bookDetail) {
        return BookDetailResponse.builder()
            .id(bookDetail.getId())
            .imageUrl(bookDetail.getImageUrl())
            .build();
    }

    @Builder
    private BookDetailResponse(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

}
