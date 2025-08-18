package com.bivago_api.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bivago_api.app.dto.auth.AuthRefreshDTO;
import com.bivago_api.app.dto.auth.AuthResponseDTO;
import com.bivago_api.app.dto.auth.AuthSigninDTO;
import com.bivago_api.app.dto.auth.AuthSignupDTO;
import com.bivago_api.app.services.AuthService;
import com.bivago_api.domain.models.User;
import com.bivago_api.shared.utils.AsyncResultHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authS;
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody @Valid AuthSignupDTO request) {
        return null;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody @Valid AuthSigninDTO request) { return ResponseEntity.ok(AsyncResultHandler.await(authS.signin(request))); }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestBody @Valid AuthRefreshDTO request) {
        return null;
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal User user) {
        // TO-DO: Deletar o Token
        return ResponseEntity.noContent().build();
    }

}
