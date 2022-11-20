package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.dto.UserBaseDataDto;
import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.domain.review.Review;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.review.ReviewMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {
            ReviewMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "userBaseData", expression = "java(mapBase(user))")
    @Mapping(target = "city", source = "user.location.city")
    @Mapping(target = "country", source = "user.location.country")
    @Mapping(target = "reviews", source = "user.reviewsOnMe")
    @Mapping(target = "reviewsPage", source = "reviewsPage")
    UserDto map(User user, Page<Review> reviewsPage);

    UserBaseDataDto mapBase(User user);
}
