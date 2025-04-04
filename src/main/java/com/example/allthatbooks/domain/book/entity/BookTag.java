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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static BookTag createBookTag(Tag tagName, Book book) {
        return BookTag.builder()
            .tagName(tagName)
            .book(book)
            .build();
    }

    public void updateTag(Tag tagName) {
        this.tagName = tagName;
    }

    @Builder
    private BookTag(Long id, Tag tagName, Book book) {
        this.id = id;
        this.tagName = tagName;
        this.book = book;
    }

}
