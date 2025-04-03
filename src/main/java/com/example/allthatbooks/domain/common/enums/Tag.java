package com.example.allthatbooks.domain.common.enums;

import lombok.Getter;

@Getter
public enum Tag {
    HUMANITIES("인문"),
    CHILDREN("유아"),
    BIOGRAPHY("인물"),
    SELF_HELP("자기 계발"),
    SCIENCE("과학");

    private final String value;

    Tag(String value) {
        this.value = value;
    }

}
