package br.com.vhugo1102.app_contato_estudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.vhugo1102.app_contato_estudo.model.Contato;
import br.com.vhugo1102.app_contato_estudo.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/contatos") // Caminho para os endpoints de Contato
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    // Criar um novo contato - Endpoint: POST /contatos
    @Operation(summary = "Cria um novo contato para uma pessoa.")
    @PostMapping
    public ResponseEntity<ContatoDTO> criarContato(@RequestBody Contato contato) {
        ContatoDTO novoContatoDTO = contatoService.save(contato);
        if (novoContatoDTO != null) {
            return ResponseEntity.ok(novoContatoDTO);  // Retorna 200 OK com o ContatoDTO
        }
        return ResponseEntity.badRequest().build(); // Retorna 400 caso haja erro
    }

    // Buscar um contato por ID - Endpoint: GET /contatos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarContatoPorId(@PathVariable Long id) {
        ContatoDTO contatoDTO = contatoService.findById(id);
        if (contatoDTO != null) {
            return ResponseEntity.ok(contatoDTO);  // Retorna 200 OK com o ContatoDTO
        }
        return ResponseEntity.notFound().build(); // Retorna 404 caso não encontre o contato
    }

    // Listar todos os contatos - Endpoint: GET /contatos
    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarContatos() {
        List<ContatoDTO> contatosDTO = contatoService.findAll();
        return ResponseEntity.ok(contatosDTO);  // Retorna 200 OK com a lista de ContatoDTOs
    }

    // Atualizar um contato - Endpoint: PUT /contatos/{id}
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
