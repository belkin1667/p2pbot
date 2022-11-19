package com.l2lhackathon.peers.controller.review.dto;

import com.l2lhackathon.peers.controller.user.dto.UserBaseDataDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private UserBaseDataDto author;
    private String text;
}