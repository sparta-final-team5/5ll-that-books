package com.example.allthatbooks.domain.book.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookDetailImageRequest {

    private Long id;
    private String imageUrl;

}
