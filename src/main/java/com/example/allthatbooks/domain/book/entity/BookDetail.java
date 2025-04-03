package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.domain.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "book_detail")
public class BookDetail extends Timestamped{

    @Id
    @Column(name = "book_detail_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String imageUrl;

    private LocalDateTime deletedAt;

    public static BookDetail createBookDetail(String imageUrl) {
        return BookDetail.builder()
            .imageUrl(imageUrl)
            .build();
    }

    @Builder
    private BookDetail(String imageUrl, LocalDateTime deletedAt) {
        this.imageUrl = imageUrl;
        this.deletedAt = deletedAt;
    }

}
