package com.example.allthatbooks.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ImageUrl {

    private String imageUrl;

    @Builder
    private ImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
