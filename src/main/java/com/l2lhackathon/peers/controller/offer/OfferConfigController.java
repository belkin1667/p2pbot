package com.l2lhackathon.peers.controller.offer;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferConfigController {

    @PostMapping(value = "/offers/configuration", consumes = {"application/json"})
    public Long uploadOfferConfiguration(@RequestBody OfferConfigDto config) {
        System.out.println(config.toString());
        return 1L;
    }
}
