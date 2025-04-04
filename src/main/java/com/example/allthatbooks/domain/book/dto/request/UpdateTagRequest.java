package com.example.allthatbooks.domain.book.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class UpdateTagRequest {

    @Size(min = 1, max = 10, message = "태그 갯수는 최소 1개 이상, 최대 10개 이하만 가능합니다.")
    private List<BookTagRequest> tags;

}
