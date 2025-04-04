package com.example.allthatbooks.domain.book.dto.request;

import com.example.allthatbooks.common.enums.Tag;
import com.example.allthatbooks.common.dto.ImageUrl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CreateBookRequest {

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

    @Size(min = 1, max = 10, message = "태그 갯수는 최소 1개 이상, 최대 10개 이하만 가능합니다.")
    private List<Tag> tags;

    @Size(max = 10, message = "이미지는 최대 10장까지 등록 가능합니다.")
    private List<ImageUrl> images = new ArrayList<>();

}
