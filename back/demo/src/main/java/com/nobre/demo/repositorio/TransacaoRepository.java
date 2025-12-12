package com.nobre.demo.repositorio;

import com.nobre.demo.DTO.Transacao.ListarTransacaoDTO;
import com.nobre.demo.model.Transacao;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByUsuarioId(long usuarioId, Pageable paginavel);

    Page<Transacao> findByValor(double valor, Pageable pageable);

    List<Transacao> findByValorGreaterThan(double valor);

    List<Transacao> findByValorLessThan(double valor);

   

}
