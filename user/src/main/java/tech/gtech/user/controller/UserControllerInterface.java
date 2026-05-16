package tech.gtech.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.dto.UserDto;

import java.util.UUID;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public interface UserControllerInterface {

    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário e o salva no banco de dados. Este processo também pode engatilhar o envio de e-mail de boas-vindas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content)
    })
    @PostMapping
    ResponseEntity<UserModel> createUser(
            @Parameter(description = "Objeto contendo os dados do usuário a ser criado", required = true)
            @RequestBody UserDto user);


    @Operation(summary = "Buscar usuário por ID", description = "Retorna os detalhes de um usuário específico utilizando seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado na base de dados", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<UserModel> getUserById(
            @Parameter(description = "Identificador exclusivo (UUID) do usuário", required = true)
            @PathVariable UUID id);


    @Operation(summary = "Listar todos os usuários", description = "Retorna uma coleção iterável com todos os usuários cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso")
    @GetMapping
    ResponseEntity<Iterable<UserModel>> getAllUsers();


    @Operation(summary = "Deletar usuário", description = "Apaga um usuário da base de dados através de seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso (sem retorno de conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para deleção", content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "Identificador exclusivo (UUID) do usuário a ser deletado", required = true)
            @PathVariable UUID id);


    @Operation(summary = "Atualizar usuário", description = "Atualiza as informações de um usuário já existente baseando-se no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuário submetido para atualização não foi encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content)
    })
    @PutMapping
    ResponseEntity<UserModel> updateUser(
            @Parameter(description = "Objeto contendo os novos dados do usuário para atualização", required = true)
            @RequestBody UserDto user);
}