package com.l2lhackathon.peers.controller.user;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import com.l2lhackathon.peers.controller.user.dto.UserBaseDataDto;
import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.controller.user.entity.Review;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.controller.user.repository.ReviewRepository;
import com.l2lhackathon.peers.controller.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {

    private final UsersRepository usersRepository;
    private final ReviewRepository reviewRepository;

    public UserDataService(UsersRepository usersRepository, ReviewRepository reviewRepository) {
        this.usersRepository = usersRepository;
        this.reviewRepository = reviewRepository;
    }

    UserDto getUser(Long id) {
        Optional<User> user = usersRepository.findById(id);
        List<Review> reviewList = user.get().getReviewsOnMe();
        List<ReviewDto> reviewDto = new ArrayList<>();

        for (Review review : reviewList) {
            reviewDto.add(ReviewDto.builder()
                    .id(review.getId())
                    .text(review.getText())
                    .author(UserBaseDataDto.builder()
                            .id(usersRepository.findById(id).get().getId())
                            .firstName(usersRepository.findById(id).get().getFirstName())
                            .lastName(usersRepository.findById(id).get().getLastName())
                            .telegramLogin(usersRepository.findById(id).get().getTelegramLogin())
                            .build())
                    .build());
        }

        return UserDto.builder()
              .userBaseData(UserBaseDataDto.builder()
                      .id(user.get().getId())
                      .firstName(user.get().getFirstName())
                      .lastName(user.get().getLastName())
                      .telegramLogin(user.get().getTelegramLogin())
                      .build())
              .city(user.get().getLocation().getCity())
              .country(user.get().getLocation().getCountry())
              .rating(user.get().getRating())
              .reviews(reviewDto)
              .build();
    }
}
