package com.l2lhackathon.peers.controller.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import io.swagger.v3.oas.annotations.servers.Server;

@Server
public class UserService {

    UserDto getUser(Long id) {
      return new UserDto();
    }
}
