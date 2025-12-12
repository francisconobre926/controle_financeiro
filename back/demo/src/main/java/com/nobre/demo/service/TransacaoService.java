package com.nobre.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.nobre.demo.DTO.Transacao.CriarTransacaoDTO;
import com.nobre.demo.DTO.Transacao.ListarTransacaoDTO;
import com.nobre.demo.model.Categoria;
import com.nobre.demo.model.Transacao;
import com.nobre.demo.model.Usuario;
import com.nobre.demo.repositorio.CategoriaRepository;
import com.nobre.demo.repositorio.TransacaoRepository;
import com.nobre.demo.repositorio.UsuarioRepository;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Long criarTransacao(@RequestBody CriarTransacaoDTO criarTransacaoDTO) {
        Usuario usuarioExistente = usuarioRepository.findByIdAndAtivoTrue(criarTransacaoDTO.clienteID())
                .orElseThrow(() -> new RuntimeException("usuario Invalido!"));

        Categoria categoria = categoriaRepository.findById(criarTransacaoDTO.categoriaID())
                .orElseThrow(() -> new RuntimeException("categoria nao encontrada!"));

        Transacao transacao = new Transacao();

        transacao.setCategoria(categoria);
        transacao.setUsuario(usuarioExistente);
        transacao.setValor(criarTransacaoDTO.valor());
        transacao.setDescricao(criarTransacaoDTO.descricao());

        return transacaoRepository.save(transacao).getId();
    }

    public Page<ListarTransacaoDTO> listarTransacao(@PathVariable Long usuarioID, Pageable paginacao) {

        Usuario usuario = usuarioService.retornarUsuarioPorID(usuarioID)
                .orElseThrow(() -> new RuntimeException("Nao existe transacao com esse usuario"));

        Page<Transacao> ListaTransacao = transacaoRepository.findByUsuarioId(usuario.getId(), paginacao);

        return ListaTransacao.map(transacao -> new ListarTransacaoDTO(
                
                transacao.getData(),
                transacao.getDescricao(),
                transacao.getUsuario().getNome(),
                transacao.getCategoria().getNome(),
                transacao.getValor()

        ));

    }

}
