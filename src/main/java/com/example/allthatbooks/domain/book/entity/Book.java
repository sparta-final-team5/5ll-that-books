package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.domain.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "books")
public class Book extends Timestamped {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String translator;

    private Integer quantity;

    private Integer price;

    private String info;

    private String thumbnailUrl;

    private LocalDateTime deletedAt;
}
