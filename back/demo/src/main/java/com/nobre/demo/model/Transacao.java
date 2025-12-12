package com.nobre.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Setter;   
import lombok.Getter;   



@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private Date data;
    private String descricao;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
   

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
}
