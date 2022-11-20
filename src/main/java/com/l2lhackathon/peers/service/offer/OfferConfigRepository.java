package com.l2lhackathon.peers.service.offer;

import com.l2lhackathon.peers.domain.offer.OfferConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferConfigRepository extends JpaRepository<OfferConfig, Long> {
}
