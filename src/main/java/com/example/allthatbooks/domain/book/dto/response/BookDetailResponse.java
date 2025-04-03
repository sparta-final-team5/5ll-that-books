package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.BookDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDetailResponse {

    private String imageUrl;

    public static BookDetailResponse toDto(BookDetail bookDetail) {
        return BookDetailResponse.builder()
            .imageUrl(bookDetail.getImageUrl())
            .build();
    }

    @Builder
    private BookDetailResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
