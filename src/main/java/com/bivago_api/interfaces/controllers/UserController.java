package com.bivago_api.interfaces.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bivago_api.app.dto.user.UserDetailsDTO;
import com.bivago_api.app.dto.user.UserRequestDTO;
import com.bivago_api.app.dto.user.UserResponseDTO;
import com.bivago_api.app.dto.user.UserUpdateDTO;
import com.bivago_api.app.services.UserService;
import com.bivago_api.shared.utils.AsyncResultHandler;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userS;
    
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Formato de Email/CPF inválido ou Nome muito curto ou longo"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado. Os ID(s) provido(s) não pertence(m) há nenhuma entidade no sistema"),
        @ApiResponse(responseCode = "409", description = "Email/CPF único já está sendo utilizado"),
        @ApiResponse(responseCode = "422", description = "Senha deve satisfazer os requisitos de segurança")
    })
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UserRequestDTO request) { return ResponseEntity.status(HttpStatus.CREATED).body(AsyncResultHandler.await(userS.create(request))); }



    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista todos os Usuários")
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> readAll() { return ResponseEntity.ok(AsyncResultHandler.await(userS.readAll())); }



    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retorna detalhes do Usuário indentificado pelo ID provido"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> readById(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(userS.readById(id))); }



    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualiza o Usuário indentificado pelo ID provido. Apenas os campos específicados serão atualizados<br>Se um campo for passado null, ele não será alterado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO data) { return ResponseEntity.ok(AsyncResultHandler.await(userS.update(id, data))); }



    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) { return ResponseEntity.ok(AsyncResultHandler.await(userS.delete(id))); }

}
