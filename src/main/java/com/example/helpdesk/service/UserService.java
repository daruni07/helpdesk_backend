package com.example.helpdesk.service;


import com.example.helpdesk.dto.request.AdminDto;
import com.example.helpdesk.enums.Role;
import com.example.helpdesk.model.User;
import com.example.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    public User getUserObj(String username, String password, Role role){
        User user =  new User(username,password,role);
        return userRepository.save(user);
    }

    public void addAdmin(AdminDto adminDto) {
        // Prepare User using AdminDto
        User user = new User(
                adminDto.username(),
                passwordEncoder.encode(adminDto.password()),
                Role.ADMIN
        );

        // Save user in DB
        userRepository.save(user);
    }
}
