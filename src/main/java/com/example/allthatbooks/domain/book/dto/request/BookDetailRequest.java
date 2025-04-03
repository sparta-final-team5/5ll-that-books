package com.example.allthatbooks.domain.book.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookDetailRequest {

    private Long id;
    private String imageUrl;

}
