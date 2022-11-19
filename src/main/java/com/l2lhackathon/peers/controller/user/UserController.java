package com.l2lhackathon.peers.controller.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    private final UserDataService userDataService;

    @GetMapping("users/{id}")
    public UserDto getUser(@PathVariable Long id) {
        if (id == null) {
            throw new PeersEntityNotFoundException(User.class, id);
        }
        return userDataService.getUser(id);
    }
}
