package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.controller.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    //@Mapping(target = "userBaseData", ignore = true)
    //@Mapping(target = "reviews", ignore = true)
    //@Mapping(target = "city", source = "location.city")
    //@Mapping(target = "country", source = "location.country")
    UserDto map(User user);

}
