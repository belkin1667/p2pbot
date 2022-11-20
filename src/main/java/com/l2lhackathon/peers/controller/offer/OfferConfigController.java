package com.l2lhackathon.peers.controller.offer;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import com.l2lhackathon.peers.controller.offer.dto.OfferConfigStatusDto;
import com.l2lhackathon.peers.controller.offer.dto.Update;
import com.l2lhackathon.peers.service.offer.OfferConfigFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping(value = "/offers/configuration/{offerConfigId}")
    public void updateActivityStatus(@PathVariable Long offerConfigId, @RequestBody Update<OfferConfigStatusDto> update) {
        offerConfigFacade.updateActivityStatus(offerConfigId, update.get());
    }
}
