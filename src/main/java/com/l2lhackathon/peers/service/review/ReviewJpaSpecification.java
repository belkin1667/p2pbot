package com.l2lhackathon.peers.service.review;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.l2lhackathon.peers.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class ReviewJpaSpecification implements Specification<Review> {

    private final ReviewFilter filter;

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("user").get("telegramId"), filter.getUserTelegramId());
    }
}
