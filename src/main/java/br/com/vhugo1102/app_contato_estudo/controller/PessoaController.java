package br.com.vhugo1102.app_contato_estudo.controller;

import java.util.Optional;

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

import br.com.vhugo1102.app_contato_estudo.DTO.PessoaDTO;
import br.com.vhugo1102.app_contato_estudo.model.Pessoa;
import br.com.vhugo1102.app_contato_estudo.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pessoa") // caminho para os endpoint
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	

	// Criar uma nova Pessoa - Endpoint: POST /pessoas
    @Operation(summary = "Cria uma nova pessoa. Preenche todos os dados obrigatórios para adicionar uma pessoa.")
    @PostMapping
	public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody Pessoa pessoa) {
		PessoaDTO novaPessoaDTO = pessoaService.save(pessoa); // Correto: recebe um PessoaDTO
		return ResponseEntity.ok(novaPessoaDTO);
	}

	// Buscar pessoa por ID - Endpoint: GET /pessoas/{id}
    @Operation(summary = "Busca uma pessoa pelo ID fornecido. Retorna detalhes da pessoa.")
    @GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
		Optional<PessoaDTO> pessoaDTO = pessoaService.findById(id);
		return pessoaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
    
    @Operation(summary = "Atualiza uma pessoa existente com base no ID fornecido.")
    @PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		pessoa.setId(id); // Define o ID recebido na URL
		PessoaDTO pessoaAtualizada = pessoaService.update(pessoa);

		if (pessoaAtualizada == null) {
			return ResponseEntity.notFound().build(); // Retorna 404 se a pessoa não existir
		}

		return ResponseEntity.ok(pessoaAtualizada); // Retorna 200 com o DTO atualizado
	}
    
    @Operation(summary = "Deleta uma pessoa com base no ID fornecido.")
	@DeleteMapping("/{id}") // DELETE /pessoas/{id}
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
	    boolean isDeleted = pessoaService.delete(id); // Chama o serviço para excluir a pessoa

	    if (isDeleted) {
	        return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
	    } else {
	        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se a pessoa não for encontrada
	    }
	}


}
