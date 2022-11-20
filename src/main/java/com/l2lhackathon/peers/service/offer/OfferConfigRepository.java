package com.l2lhackathon.peers.service.offer;

import java.util.List;

import com.l2lhackathon.peers.domain.offer.OfferConfig;
import com.l2lhackathon.peers.domain.offer.OfferConfigStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferConfigRepository extends JpaRepository<OfferConfig, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM offer_config WHERE status = :status")
    List<OfferConfig> getAllOffers(String status);

}
