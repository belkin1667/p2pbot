package com.l2lhackathon.peers.controller.user.repository;

import com.l2lhackathon.peers.controller.user.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
