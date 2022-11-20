package com.l2lhackathon.peers.service.offer;

import java.util.List;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import com.l2lhackathon.peers.controller.offer.dto.OfferConfigStatusDto;
import com.l2lhackathon.peers.domain.offer.OfferConfig;
import com.l2lhackathon.peers.domain.offer.OfferConfigStatus;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
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

    public void updateActivityStatus(Long offerConfigId, OfferConfigStatusDto status) {
        var config = findById(offerConfigId);

        config.setStatus(Enum.valueOf(OfferConfigStatus.class, status.name()));
        offerConfigRepository.save(config);
    }

    @Transactional(readOnly = true)
    public List<OfferConfig> getAllActiveOfferNames() {
        return offerConfigRepository.getAllOffers(OfferConfigStatus.ACTIVE.name());
    }

    @Transactional(readOnly = true)
    public OfferConfig findById(Long id) {
        return offerConfigRepository.findById(id)
                .orElseThrow(() -> new PeersEntityNotFoundException(OfferConfig.class, id));
    }
}
