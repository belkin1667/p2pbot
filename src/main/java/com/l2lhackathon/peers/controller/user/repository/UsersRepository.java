package com.l2lhackathon.peers.controller.user.repository;

import com.l2lhackathon.peers.controller.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
