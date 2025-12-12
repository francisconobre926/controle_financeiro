package com.nobre.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nobre.demo.DTO.usuario.Actualiza_usuarioDTO;
import com.nobre.demo.DTO.usuario.CriarUsuarioDTO;
import com.nobre.demo.DTO.usuario.ListarUsuarioDTO;
import com.nobre.demo.model.Usuario;
import com.nobre.demo.service.UsuarioService;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Long> criarUsuario(@RequestBody CriarUsuarioDTO criarusuariodto) {
        // Chama o serviço para criar o usuário
        Long usuarioId = usuarioService.criarUsuario(criarusuariodto);
        return ResponseEntity.ok(usuarioId);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarUsuarioDTO> retornarUsuarioPorID(@PathVariable("id") Long id) {

        return usuarioService.retornarUsuarioPorID(id).map(usuario -> new ListarUsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()

        )).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PatchMapping
    public ResponseEntity<?> actualizaUsuario(@RequestBody Actualiza_usuarioDTO actualiza_usuarioDTO) {

        try {
            var usuario = usuarioService.editarUsuario(actualiza_usuarioDTO);
            return ResponseEntity.ok(usuario);

        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        }

    }

    @GetMapping("/desativar/{id}")
    public ResponseEntity<?> desativarUsuario(@PathVariable Long id) {

        String usuario = usuarioService.desativarUsuario(id);

        return ResponseEntity.ok(usuario);

    }

    @GetMapping("/ativar/{id}")
    public ResponseEntity<?> ativarUsuario(@PathVariable Long id) {

        String usuario = usuarioService.ativarUsuario(id);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tam) {
        Pageable paginacao = PageRequest.of(pagina, tam);

        var usuarios = usuarioService.listarUsuarios(paginacao);

        return ResponseEntity.ok(usuarios);
    }

}
