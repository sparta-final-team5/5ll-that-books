package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
import com.example.allthatbooks.domain.common.entity.Timestamped;
import com.example.allthatbooks.domain.common.enums.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "books")
public class Book extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String author;

    @Column(length = 30)
    private String translator;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, columnDefinition = "text")
    private String info;

    @Column(nullable = false)
    private String thumbnailUrl;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    private List<BookTag> bookTagList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    private List<BookDetail> bookDetailList = new ArrayList<>();

    private LocalDateTime deletedAt;

    public static Book createBook(CreateBookRequest request) {
        return Book.builder()
            .title(request.getTitle())
            .author(request.getAuthor())
            .translator(request.getTranslator())
            .quantity(request.getQuantity())
            .price(request.getPrice())
            .info(request.getInfo())
            .thumbnailUrl(request.getThumbnailUrl())
            .build();
    }

    public void addTag(BookTag bookTag) {
        this.bookTagList.add(bookTag);
    }

    public void addDetail(BookDetail bookDetail) {
        this.bookDetailList.add(bookDetail);
    }

    public void updateBook(UpdateBookRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.translator = request.getTranslator();
        this.quantity = request.getQuantity();
        this.price = request.getPrice();
        this.info = request.getInfo();
        this.thumbnailUrl = request.getThumbnailUrl();
    }

    public void deleteBook() {
        this.deletedAt = LocalDateTime.now();
    }

    @Builder
    private Book(Long id, String title, String author, String translator, Integer quantity, Integer price, String info, String thumbnailUrl, LocalDateTime deletedAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.translator = translator;
        this.quantity = quantity;
        this.price = price;
        this.info = info;
        this.thumbnailUrl = thumbnailUrl;
        this.deletedAt = deletedAt;
    }

}
