package com.example.allthatbooks.domain.book.entity;

import com.example.allthatbooks.common.entity.Timestamped;
import com.example.allthatbooks.domain.book.dto.request.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookTag> bookTagSet = new HashSet<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookDetailImage> bookDetailImageList = new ArrayList<>();

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

    public void updateBasicInfo(UpdateBookRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.translator = request.getTranslator();
        this.quantity = request.getQuantity();
        this.price = request.getPrice();
        this.info = request.getInfo();
        this.thumbnailUrl = request.getThumbnailUrl();
    }

    public void updateTags(UpdateTagRequest request) {
        Set<Long> requestTagIds = request.getTags().stream()
            .filter(tag -> tag.getId() != null)
            .map(BookTagRequest::getId)
            .collect(Collectors.toSet());

        // 현재 Hard Delete 정책이어서 요청에 없는 태그는 DB에서 제거됨
        bookTagSet.removeIf(tag -> !requestTagIds.contains(tag.getId()));

        // 추가 및 업데이트
        for (BookTagRequest tag : request.getTags()) {
            if (tag.getId() != null) {
                bookTagSet.stream()
                    .filter(t -> t.getId().equals(tag.getId()))
                    .findFirst()
                    .ifPresent(t -> t.updateTag(tag.getTag()));
            } else {
                BookTag newTag = BookTag.createBookTag(tag.getTag(), this);
                bookTagSet.add(newTag);
            }
        }
    }

    public void updateDetailImages(UpdateDetailImageRequest request) {
        Set<Long> requestImageIds = request.getImages().stream()
            .filter(img -> img.getId() != null)
            .map(BookDetailImageRequest::getId)
            .collect(Collectors.toSet());

        // 요청에 없는 이미지 -> Soft Delete 처리
        bookDetailImageList.forEach(image -> {
            if (!requestImageIds.contains(image.getId()) && image.getDeletedAt() == null) {
                image.deleteBookDetailImage();
            }
        });

        // 추가 및 업데이트
        for (BookDetailImageRequest img : request.getImages()) {
            if (img.getId() != null) {
                bookDetailImageList.stream()
                    .filter(dImg -> dImg.getId().equals(img.getId()))
                    .findFirst()
                    .ifPresent(dImg -> dImg.updateImageUrl(img.getImageUrl()));
            } else {
                BookDetailImage newImage = BookDetailImage.createBookDetail(img.getImageUrl(), this);
                bookDetailImageList.add(newImage);
            }
        }
    }

    public void addTag(BookTag bookTag) {
        this.bookTagSet.add(bookTag);
    }

    public void addDetail(BookDetailImage bookDetailImage) {
        this.bookDetailImageList.add(bookDetailImage);
    }

    public void deleteBook() {
        this.deletedAt = LocalDateTime.now();

        for (BookDetailImage bookDetailImage : bookDetailImageList) {
            bookDetailImage.deleteBookDetailImage();
        }
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
