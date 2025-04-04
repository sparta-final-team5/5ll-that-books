package com.example.allthatbooks.domain.book.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class UpdateDetailImageRequest {

    @Size(max = 10, message = "이미지는 최대 10장까지 등록 가능합니다.")
    private List<BookDetailImageRequest> images = new ArrayList<>();

}
