package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    UserDto map(User user);

}
