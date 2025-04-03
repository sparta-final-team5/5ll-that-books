package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.common.enums.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "book_tag")
public class BookTag {

    @Id
    @Column(name = "book_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tag tagName;

    public static BookTag createBookTag(Tag tagName) {
        return BookTag.builder()
            .tagName(tagName)
            .build();
    }

    public static BookTag updateBookTag(Long id, Tag tagName) {
        return BookTag.builder()
            .id(id)
            .tagName(tagName)
            .build();
    }

    @Builder
    private BookTag(Long id, Tag tagName) {
        this.id = id;
        this.tagName = tagName;
    }

}
