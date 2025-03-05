package com.example.zb_account.repository;

import com.example.zb_account.domain.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailRepository {
    @Autowired
    private UserRepository userRepository;

    public AccountUser findUserById(Long id) {
        return userRepository.getReferenceById(id);
    }
}
