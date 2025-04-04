package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.BookDetailImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDetailImageResponse {

    private final Long id;
    private final String imageUrl;

    public static BookDetailImageResponse toDto(BookDetailImage bookDetailImage) {
        return BookDetailImageResponse.builder()
            .id(bookDetailImage.getId())
            .imageUrl(bookDetailImage.getImageUrl())
            .build();
    }

    @Builder
    private BookDetailImageResponse(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

}
