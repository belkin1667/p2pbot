package com.l2lhackathon.peers.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDataDto {
    private Long id;
    private String telegramLogin;
    private String firstName;
    private String lastName;
}
