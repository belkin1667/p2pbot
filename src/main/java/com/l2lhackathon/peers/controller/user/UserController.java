package com.l2lhackathon.peers.controller.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.user.UserFacade;
import com.l2lhackathon.peers.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("users/{telegramId}")
    public UserDto getUser(@PathVariable Long telegramId) {
        return userFacade.findByTelegramId(telegramId);
    }
}
