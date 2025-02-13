package br.com.vhugo1102.app_contato_estudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vhugo1102.app_contato_estudo.DTO.ContatoDTO;
import br.com.vhugo1102.app_contato_estudo.DTO.PessoaDTO;
import br.com.vhugo1102.app_contato_estudo.model.Contato;
import br.com.vhugo1102.app_contato_estudo.model.Pessoa;
import br.com.vhugo1102.app_contato_estudo.repository.ContatoRepository;
import br.com.vhugo1102.app_contato_estudo.repository.PessoaRepository;
import br.com.vhugo1102.app_contato_estudo.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoService contatoService;  // Supondo que você tenha um ContatoService

    // Criar um novo contato - Endpoint: POST /contatos
    @Operation(summary = "Cria um novo contato para uma pessoa.")
    @PostMapping
    public ResponseEntity<ContatoDTO> criarContato(@RequestBody ContatoDTO contatoDTO) {
        // Verificar se a pessoa existe com o idPessoa fornecido
        Pessoa pessoa = pessoaRepository.findById(contatoDTO.getIdPessoa())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        // Criar o objeto Contato
        Contato novoContato = new Contato();
        novoContato.setTipoContato(contatoDTO.getTipoContato());
        novoContato.setContato(contatoDTO.getContato());
        novoContato.setPessoa(pessoa);  // Associando a pessoa ao contato

        // Salvar o novo contato no banco de dados
        Contato contatoSalvo = contatoRepository.save(novoContato);

        // Criar o ContatoDTO para a resposta
     // Criar o ContatoDTO com o Contato e a PessoaDTO
        ContatoDTO responseDTO = new ContatoDTO(contatoSalvo, new PessoaDTO(contatoSalvo.getPessoa()));

        // Retornar a resposta com o ContatoDTO
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); // Retorna 201 Created
    }


    // Buscar um contato por ID - Endpoint: GET /contatos/{id}
    @Operation(summary = "Busca um contato pelo ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarContatoPorId(@PathVariable Long id) {
        ContatoDTO contatoDTO = contatoService.findById(id);
        if (contatoDTO != null) {
            return ResponseEntity.ok(contatoDTO);  // Retorna 200 OK com o ContatoDTO
        }
        return ResponseEntity.notFound().build(); // Retorna 404 caso não encontre o contato
    }

    // Listar todos os contatos - Endpoint: GET /contatos
    @Operation(summary = "Lista todos os contatos cadastrados.")
    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarContatos() {
        List<ContatoDTO> contatosDTO = contatoService.findAll();
        return ResponseEntity.ok(contatosDTO);  // Retorna 200 OK com a lista de ContatoDTOs
    }

    // Atualizar um contato - Endpoint: PUT /contatos/{id}
    @Operation(summary = "Atualiza as informações de um contato.")
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
        contato.setId(id);  // Define o ID recebido na URL
        ContatoDTO contatoAtualizado = contatoService.update(contato);
        if (contatoAtualizado != null) {
            return ResponseEntity.ok(contatoAtualizado);  // Retorna 200 OK com o ContatoDTO atualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se o contato não for encontrado
    }

    // Deletar um contato - Endpoint: DELETE /contatos/{id}
    @Operation(summary = "Deleta um contato baseado no ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        try {
            contatoService.delete(id);  // Chama o serviço para excluir o contato
            return ResponseEntity.noContent().build();  // Retorna 204 sem conteúdo
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o contato não for encontrado
        }
    }
}
