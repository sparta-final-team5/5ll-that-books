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
@Table(name = "book_detail")
public class BookDetail extends Timestamped{

    @Id
    @Column(name = "book_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image_url;

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}
