package com.nobre.demo.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)    
    private long id;

    @NotBlank
    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal saldo_total;

    private boolean admin=false;

    private boolean ativo=true;


    public void desativarUsuario(){
        this.ativo=false;
    }

    public void ativarUsuario(){
        this.ativo=true;
    }
    
    @CreationTimestamp
    private Instant createdTimestamp;
    
    @UpdateTimestamp
    private Instant updatedTimestamp;




    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;


    

}
