package com.example.allthatbooks.domain.book.dto.request;

import com.example.allthatbooks.common.enums.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookTagRequest {

    private Long id;
    private Tag tag;

}
