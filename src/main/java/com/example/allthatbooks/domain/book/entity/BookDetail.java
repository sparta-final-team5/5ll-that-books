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
@Table(name = "book_detail")
public class BookDetail extends Timestamped {

    @Id
    @Column(name = "book_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private LocalDateTime deletedAt;

    public static BookDetail createBookDetail(String imageUrl) {
        return BookDetail.builder()
            .imageUrl(imageUrl)
            .build();
    }

    public static BookDetail updateBookDetail(Long id, String imageUrl) {
        return BookDetail.builder()
            .id(id)
            .imageUrl(imageUrl)
            .build();
    }

    public void deleteBookDetail() {
        this.deletedAt = LocalDateTime.now();
    }

    @Builder
    private BookDetail(Long id, String imageUrl, LocalDateTime deletedAt) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.deletedAt = deletedAt;
    }

}
