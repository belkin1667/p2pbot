package com.l2lhackathon.peers.service.offer;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import com.l2lhackathon.peers.domain.offer.OfferConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferConfigFacade {

    private final OfferConfigRepository offerConfigRepository;

    public Long uploadOfferConfiguration(OfferConfigDto config) {
        OfferConfig offerConfig = new OfferConfig();
        offerConfig.init(config);
        return offerConfigRepository.save(offerConfig).getId();
    }
}
