package com.l2lhackathon.peers.service.user;

import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new PeersEntityNotFoundException(User.class, id));
    }

    @Transactional(readOnly = true)
    public User findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new PeersEntityNotFoundException(User.class));
    }
}
