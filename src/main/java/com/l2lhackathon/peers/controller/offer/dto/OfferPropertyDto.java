package com.l2lhackathon.peers.controller.offer.dto;

import com.l2lhackathon.peers.controller.offer.dto.constraints.ConstraintDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OfferPropertyDto {
    String name;
    OfferPropertyTypeDto type;
    ConstraintDto constraint;
}
