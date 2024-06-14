package com.sha.spring_boot_book_seller.service.serviceImpl;

import com.sha.spring_boot_book_seller.model.Role;
import com.sha.spring_boot_book_seller.model.User;
import com.sha.spring_boot_book_seller.repository.IUserRepository;
import com.sha.spring_boot_book_seller.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void makeadmin(String username) {
        userRepository.updateUserRole(username, Role.ADMIN);
    }
}
