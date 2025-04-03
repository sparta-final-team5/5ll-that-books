package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.common.enums.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "book_tag")
public class BookTag {

    @Id
    @Column(name = "book_tag_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Tag tagName;

    public static BookTag createBookTag(Tag tagName) {
        return BookTag.builder()
            .tagName(tagName)
            .build();
    }

    @Builder
    private BookTag(Tag tagName) {
        this.tagName = tagName;
    }

}
