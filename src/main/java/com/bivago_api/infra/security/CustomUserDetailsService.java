package com.bivago_api.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final IUserRepository userR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userR.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário com email " + username + " não foi encontrado"));

        // List<GrantedAuthority> authorities = user.getRole()
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
    }

}
