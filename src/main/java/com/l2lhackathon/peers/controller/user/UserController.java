package com.l2lhackathon.peers.controller.user;

import java.math.BigDecimal;
import java.util.List;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import com.l2lhackathon.peers.controller.user.dto.UserBaseDataDto;
import com.l2lhackathon.peers.controller.user.dto.UserDto;
import com.l2lhackathon.peers.domain.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        if (id != 1) {
            throw new PeersEntityNotFoundException(User.class, id);
        }
        return UserDto.builder()
                .userBaseData(UserBaseDataDto.builder()
                        .id(1L)
                        .firstName("Михаил")
                        .lastName("Белкин")
                        .telegramLogin("@belkinmike")
                        .build())
                .city("Котор")
                .country("Черногория")
                .rating(new BigDecimal("4.6"))
                .photoUrl("https://sun9-west.userapi.com/sun9-63/s/v1/if1/_lTjUY1ENKHfO5KgW6Juh2Zdsle6-0EwUPIABGtEBalreErLmlfMvX7aV0zMxktIGvNNJclv.jpg?size=2048x1536&quality=96&type=album")
                .reviews(new PageImpl<>(List.of(
                        ReviewDto.builder()
                                .id(123L)
                                .text("Миша - отличный джавист!")
                                .author(UserBaseDataDto.builder()
                                        .id(2L)
                                        .firstName("Михаил")
                                        .firstName("Майданчик")
                                        .telegramLogin("@mikmay")
                                        .build())
                                .build(),
                        ReviewDto.builder()
                                .id(124L)
                                .text("Помог разработать бота")
                                .author(UserBaseDataDto.builder()
                                        .id(3L)
                                        .firstName("Pavel")
                                        .firstName("Kolesnikov")
                                        .telegramLogin("@PavelKolesnikov")
                                        .build())
                                .build()
                )))
                .build();
    }
}
