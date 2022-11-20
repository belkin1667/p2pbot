package com.l2lhackathon.peers.controller.offer;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import com.l2lhackathon.peers.service.offer.OfferConfigFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfferConfigController {

    private final OfferConfigFacade offerConfigFacade;

    @PostMapping(value = "/offers/configuration", consumes = {"application/json"})
    public Long uploadOfferConfiguration(@RequestBody OfferConfigDto config) {
        return offerConfigFacade.uploadOfferConfiguration(config);
    }
}
