package com.l2lhackathon.peers.controller.offer.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OfferConfigDto {
    List<OfferPropertyDto> properties;
    String offerName;
}
