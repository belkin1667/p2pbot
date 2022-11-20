package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.domain.review.Review;
import com.l2lhackathon.peers.domain.user.UserWith;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserFacade {

    private final UserQueryService userQueryService;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto findByTelegramId(Long telegramId) {
        UserWith<Page<Review>> userWithReviewPage = userQueryService.getUserByTelegramId(telegramId);
        return userMapper.map(userWithReviewPage.getUser(), userWithReviewPage.getData());
    }
}
