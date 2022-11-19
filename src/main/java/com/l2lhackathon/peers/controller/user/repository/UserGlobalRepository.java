package com.l2lhackathon.peers.controller.user.repository;

import com.l2lhackathon.peers.controller.user.entity.UserGlobalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGlobalRepository extends JpaRepository<UserGlobalEntity, Long> {
}
