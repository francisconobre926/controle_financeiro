package com.nobre.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.nobre.demo.DTO.usuario.Actualiza_usuarioDTO;
import com.nobre.demo.DTO.usuario.CriarUsuarioDTO;
import com.nobre.demo.DTO.usuario.ListarUsuarioDTO;
import com.nobre.demo.model.Usuario;
import com.nobre.demo.repositorio.UsuarioRepository;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long criarUsuario(@RequestBody CriarUsuarioDTO criarUsuariodto) {

        var entity = new Usuario();
        entity.setNome(criarUsuariodto.nome());
        entity.setEmail(criarUsuariodto.email());
        entity.setPassword(criarUsuariodto.password());
        entity.setAdmin(false);
        entity.setAtivo(true);

        var usuarioSalvo = usuarioRepository.save(entity);

        return usuarioSalvo.getId();
    }

    public Optional<Usuario> retornarUsuarioPorID(Long id) {
        return usuarioRepository.findByIdAndAtivoTrue(id); 
    }

    public Usuario editarUsuario(@RequestBody Actualiza_usuarioDTO actualiza_usuarioDTO) {

        Usuario usuarioExistente = usuarioRepository.findById(actualiza_usuarioDTO.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(actualiza_usuarioDTO.email() != null) {

            usuarioExistente.setEmail(actualiza_usuarioDTO.email());
        }

        if(actualiza_usuarioDTO.nome() != null){

            usuarioExistente.setNome(actualiza_usuarioDTO.nome());
        }
        if(actualiza_usuarioDTO.password() != null){

            usuarioExistente.setPassword(actualiza_usuarioDTO.password());
        }
        

        return usuarioRepository.save(usuarioExistente);
    }



    public String desativarUsuario(@PathVariable Long id) {

        Usuario usuario = usuarioRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        usuario.desativarUsuario();
        usuarioRepository.save(usuario);

        return "Usuario Deletado!";
    }


    public String ativarUsuario(@PathVariable Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        usuario.ativarUsuario();
        usuarioRepository.save(usuario);

        return "Usuario Ativado!";
    }


    public Page<ListarUsuarioDTO> listarUsuarios(Pageable paginacao) {
        return usuarioRepository.findByAtivoTrue(paginacao);

    }

}
