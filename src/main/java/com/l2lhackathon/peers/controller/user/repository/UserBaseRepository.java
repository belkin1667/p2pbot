package com.l2lhackathon.peers.controller.user.repository;

import com.l2lhackathon.peers.controller.user.entity.UserBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBaseEntity, Long> {

}
