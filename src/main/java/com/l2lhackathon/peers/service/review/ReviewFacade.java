package com.l2lhackathon.peers.service.review;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewFacade {

    private final ReviewQueryService reviewQueryService;
    private final ReviewMapper reviewMapper;

    public Page<ReviewDto> findReviews(Long telegramId, Pageable pageable) {
        return reviewMapper.map(reviewQueryService.findReviews(ReviewFilter.builder().userTelegramId(telegramId).build(), pageable));
    }
}
