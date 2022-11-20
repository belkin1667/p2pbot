package com.l2lhackathon.peers.controller.review;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import com.l2lhackathon.peers.service.review.ReviewFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewFacade reviewFacade;

    @GetMapping("/reviews")
    public Page<ReviewDto> getReviewPage(Long telegramUserId, Pageable pageable) {
        return reviewFacade.findReviews(telegramUserId, pageable);
    }
}
