package com.example.allthatbooks.domain.review.repository;

import com.example.allthatbooks.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
