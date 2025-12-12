package com.nobre.demo.DTO.Transacao;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListarTransacaoDTO {


    private Date data;
    private String descricao;
    private String nomeUsuario;
    private String nomeCategoria;
    private BigDecimal valor;
    
    

}
