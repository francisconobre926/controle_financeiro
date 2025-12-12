package com.nobre.demo.DTO.Transacao;

import java.math.BigDecimal;

public record CriarTransacaoDTO(Long clienteID, String descricao, BigDecimal valor, Long categoriaID ) {

}
