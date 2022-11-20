package com.l2lhackathon.peers.service.offer;

import com.l2lhackathon.peers.domain.offer.OfferProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<OfferProperty, Long> {
}
