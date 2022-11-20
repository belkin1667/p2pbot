package com.l2lhackathon.peers.service.review;

import com.l2lhackathon.peers.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<Review> findReviews(ReviewFilter filter, Pageable pageable) {
        return reviewRepository.findAll(new ReviewJpaSpecification(filter), pageable);
    }
}
