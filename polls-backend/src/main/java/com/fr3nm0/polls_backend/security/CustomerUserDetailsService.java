package com.fr3nm0.polls_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr3nm0.polls_backend.entities.UserEntity;
import com.fr3nm0.polls_backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        return new User(
            user.getEmail(),
            user.getEncryptedPassword(),
            authorities
        );
    }
}
