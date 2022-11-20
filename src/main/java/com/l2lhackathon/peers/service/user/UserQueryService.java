package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.domain.review.Review;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.domain.user.UserWith;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.review.ReviewFilter;
import com.l2lhackathon.peers.service.review.ReviewJpaSpecification;
import com.l2lhackathon.peers.service.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository usersRepository;
    private final ReviewRepository reviewRepository;

    public User getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new PeersEntityNotFoundException(User.class, id));
    }

    public UserWith<Page<Review>> getUserByTelegramId(Long telegramId) {
        User user = usersRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new PeersEntityNotFoundException(User.class));
        ReviewFilter filter = ReviewFilter.builder().userTelegramId(user.getTelegramId()).build();
        Page<Review> reviewPage = reviewRepository.findAll(new ReviewJpaSpecification(filter), Pageable.ofSize(10));
        return UserWith.<Page<Review>>builder()
                .user(user)
                .data(reviewPage)
                .build();
    }
}
