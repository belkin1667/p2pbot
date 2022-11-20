package com.l2lhackathon.peers.service.review;

import java.util.List;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import com.l2lhackathon.peers.domain.review.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReviewMapper {

    ReviewDto map(Review review);

    default Page<ReviewDto> map(Page<Review> review) {
        return new PageImpl<>(map(review.getContent()), review.getPageable(), review.getTotalElements());
    }

    List<ReviewDto> map(List<Review> review);

}