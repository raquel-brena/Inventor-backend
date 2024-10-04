package com.rb.auth.services;

import com.rb.auth.domain.user.User;
import com.rb.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUserById (String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
    }
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
