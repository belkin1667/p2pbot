package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserFacade {

    private final UserService userService;
    //private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
//        return userMapper.map(userService.getUserById(id));
        return null;
    }
}
