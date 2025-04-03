package com.example.allthatbooks.domain.book.dto.response;

import com.example.allthatbooks.domain.book.entity.BookTag;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookTagResponse {

    private final Long id;
    private final String code;
    private final String value;

    public static BookTagResponse toDto(BookTag bookTag) {
        return BookTagResponse.builder()
            .id(bookTag.getId())
            .code(bookTag.getTagName().name())
            .value(bookTag.getTagName().getValue())
            .build();
    }

    @Builder
    private BookTagResponse(Long id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }

}
