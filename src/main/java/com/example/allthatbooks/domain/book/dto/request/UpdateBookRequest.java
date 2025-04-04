package com.example.allthatbooks.domain.book.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateBookRequest {

    @NotBlank(message = "책 제목 입력은 필수입니다.")
    private String title;

    @NotBlank(message = "책 저자 입력은 필수입니다.")
    private String author;

    private String translator;

    @NotNull(message = "책 재고수량 입력은 필수입니다.")
    private Integer quantity;

    @NotNull(message = "책 가격 입력은 필수입니다.")
    private Integer price;

    @NotBlank(message = "책 설명 입력은 필수입니다.")
    private String info;

    @NotBlank(message = "썸네일 이미지 URL 은 필수입니다.")
    private String thumbnailUrl;

}
