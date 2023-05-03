package com.pilot.services;

import com.pilot.entties.User;
import com.pilot.exceptions.AccessDeniedException;
import com.pilot.models.RequestLogin;
import com.pilot.models.ResponseData;
import com.pilot.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService,UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseData<?> insert(RequestLogin requestLogin) {
        Optional<User> user = userRepository.findByEmail(requestLogin.getEmail());
        if (user.isPresent()) {
            return ResponseData.ok(user.get().getId());
        } else {
            userRepository.save(User.builder()
                    .email(requestLogin.getEmail())
                    .password(passwordEncoder.encode(requestLogin.getPassword() != null
                            && requestLogin.getPassword().length() > 0 ? requestLogin.getPassword() : "12345678"))
                    .build());
            return ResponseData.ok();
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(AccessDeniedException::new);
    }


}
