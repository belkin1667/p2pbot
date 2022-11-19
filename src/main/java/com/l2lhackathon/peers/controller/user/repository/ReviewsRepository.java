package com.l2lhackathon.peers.controller.user.repository;

import com.l2lhackathon.peers.controller.user.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {
}
