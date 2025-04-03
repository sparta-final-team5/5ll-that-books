package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.domain.book.dto.request.CreateBookRequest;
import com.example.allthatbooks.domain.book.dto.request.UpdateBookRequest;
import com.example.allthatbooks.common.dto.ImageUrl;
import com.example.allthatbooks.common.entity.Timestamped;
import com.example.allthatbooks.common.enums.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Set<BookTag> bookTagSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
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
        this.bookTagSet.add(bookTag);
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

        Set<BookTag> newTagSet = new HashSet<>();
        for (Tag tag : request.getTags()) {
            newTagSet.add(BookTag.createBookTag(tag));
        }
        this.bookTagSet = new HashSet<>(newTagSet);

        List<BookDetail> newDetailList = new ArrayList<>();
        for (ImageUrl image : request.getImages()) {
            newDetailList.add(BookDetail.createBookDetail(image.getImageUrl()));
        }
        this.bookDetailList = new ArrayList<>(newDetailList);
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
