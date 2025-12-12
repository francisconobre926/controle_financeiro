package com.nobre.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nobre.demo.DTO.Transacao.CriarTransacaoDTO;
import com.nobre.demo.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")



public class TransacaoController {


    @Autowired 
    private TransacaoService transacaoService;


    @PostMapping
    public ResponseEntity<?>criarTransacao(@RequestBody CriarTransacaoDTO criarTransacaoDTO){
        var transacao=transacaoService.criarTransacao(criarTransacaoDTO);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>ListarTransacao(@ PathVariable("id") Long usuarioID, @RequestParam (defaultValue = "0") int pagina, @RequestParam (defaultValue = "10") int tam){
        Pageable paginacao=PageRequest.of(pagina, tam);
        
        var transacao=transacaoService.listarTransacao(usuarioID, paginacao);

        return ResponseEntity.ok(transacao);

    }

    

    
}
