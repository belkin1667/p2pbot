package com.l2lhackathon.peers.controller.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.service.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("users/{telegramId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long telegramId) {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(userFacade.findByTelegramId(telegramId));
    }
}
