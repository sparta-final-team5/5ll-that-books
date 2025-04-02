package com.example.allthatbooks.domain.review.repository;

import com.example.allthatbooks.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
