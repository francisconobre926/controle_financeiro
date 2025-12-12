package com.nobre.demo.repositorio;

import java.util.Optional;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nobre.demo.DTO.usuario.ListarUsuarioDTO;
import com.nobre.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByIdAndAtivoTrue(Long id);

    Page <ListarUsuarioDTO> findByAtivoTrue(Pageable paginacao);
}
