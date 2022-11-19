package com.l2lhackathon.peers.controller.user.dto;

import java.math.BigDecimal;

import com.l2lhackathon.peers.controller.review.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Delegate private UserBaseDataDto userBaseData;
    private String city;
    private String country;
    private String photoUrl;
    private BigDecimal rating; //5.0
    private Page<ReviewDto> reviews;
}
