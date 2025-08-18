package com.bivago_api.interfaces.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bivago_api.app.dto.auth.AuthRefreshDTO;
import com.bivago_api.app.dto.auth.AuthResponseDTO;
import com.bivago_api.app.dto.auth.AuthSigninDTO;
import com.bivago_api.app.dto.auth.AuthSignupDTO;
import com.bivago_api.app.dto.user.UserRequestDTO;
import com.bivago_api.app.services.AuthService;
import com.bivago_api.app.services.UserService;
import com.bivago_api.domain.models.Role;
import com.bivago_api.domain.models.User;
import com.bivago_api.domain.repositories.IRoleRepository;
import com.bivago_api.shared.utils.AsyncResultHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authS;
    private final UserService userS;
    private final IRoleRepository roleR;
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody @Valid AuthSignupDTO request) {
        Role defaultRole = roleR.findByName("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role USER não existe"));
        UserRequestDTO dto = new UserRequestDTO(request, Set.of(defaultRole).stream().map(Role::getId).collect(Collectors.toSet()));
        userS.create(dto);
        return ResponseEntity.ok(AsyncResultHandler.await(authS.signin(new AuthSigninDTO(request.email(), request.password()))));
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
