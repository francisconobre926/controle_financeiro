package com.nobre.demo.DTO;
import java.math.BigDecimal;
import java.util.Date;

import com.nobre.demo.DTO.usuario.CriarUsuarioDTO;

public record TransacaoDTO(CriarUsuarioDTO usuario, CategoriaDTO categoria, String descricao, BigDecimal valor, Date data) {

}
