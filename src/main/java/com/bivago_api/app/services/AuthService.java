package com.bivago_api.app.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bivago_api.app.dto.auth.AuthResponseDTO;
import com.bivago_api.app.dto.auth.AuthSigninDTO;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IUserRepository;
import com.bivago_api.infra.security.JwtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final IUserRepository userR;
    private final JwtService jwtS;
    // private final TokenService tokenS;
    private final AuthenticationManager authenticationManager;
    
    public CompletableFuture<AuthResponseDTO> signin(AuthSigninDTO data) {
        Authentication auth;
        System.out.println("C");
        try { auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password())); } 
        catch (Exception e) { throw e; }
        System.out.println("F");
        User user = userR.findByEmail(auth.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return CompletableFuture.completedFuture(new AuthResponseDTO(jwtS.generateAccessToken(user), "null"));
    }

}
