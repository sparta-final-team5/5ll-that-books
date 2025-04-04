package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "book_detail_images")
public class BookDetailImage extends Timestamped {

    @Id
    @Column(name = "book_detail_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDateTime deletedAt;

    public static BookDetailImage createBookDetail(String imageUrl, Book book) {
        return BookDetailImage.builder()
            .imageUrl(imageUrl)
            .book(book)
            .build();
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void deleteBookDetailImage() {
        this.deletedAt = LocalDateTime.now();
    }

    @Builder
    private BookDetailImage(Long id, String imageUrl, Book book, LocalDateTime deletedAt) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.book = book;
        this.deletedAt = deletedAt;
    }
}
