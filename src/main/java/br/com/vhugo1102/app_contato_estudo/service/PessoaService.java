package br.com.vhugo1102.app_contato_estudo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vhugo1102.app_contato_estudo.DTO.PessoaDTO;
import br.com.vhugo1102.app_contato_estudo.model.Pessoa;
import br.com.vhugo1102.app_contato_estudo.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // CRUD - CREATE
    public PessoaDTO save(Pessoa pessoa) {
        // Regra de negócio
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            System.out.println("Nome da pessoa vazio.");
            return null;
        }

        try {
            Pessoa savedPessoa = pessoaRepository.save(pessoa);
            return new PessoaDTO(savedPessoa);  // Retorna o DTO
        } catch (Exception e) {
            System.out.println("Erro ao inserir pessoa: " + e.getMessage());
            return null;
        }
    }

    // CRUD - READ (buscar por ID)
    public Optional<PessoaDTO> findById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return Optional.of(new PessoaDTO(pessoa.get()));  // Convertendo a Pessoa para PessoaDTO
        }
        return Optional.empty();
    }

    // CRUD - READ (listar todas as pessoas)
    public List<PessoaDTO> findAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                      .map(PessoaDTO::new)  // Convertendo cada Pessoa para PessoaDTO
                      .collect(Collectors.toList());
    }

    // CRUD - UPDATE
    public PessoaDTO update(Pessoa pessoa) {
        Optional<Pessoa> existingPessoa = pessoaRepository.findById(pessoa.getId());
        if (existingPessoa.isPresent()) {
            Pessoa updatedPessoa = existingPessoa.get();
            updatedPessoa.setNome(pessoa.getNome());
            updatedPessoa.setEndereco(pessoa.getEndereco());
            updatedPessoa.setCep(pessoa.getCep());
            updatedPessoa.setCidade(pessoa.getCidade());
            updatedPessoa.setUf(pessoa.getUf());
            pessoaRepository.save(updatedPessoa);
            return new PessoaDTO(updatedPessoa);  // Retorna o DTO atualizado
        }
        return null;
    }

 // CRUD - DELETE
    public boolean delete(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id); // Verifica se a pessoa existe
        if (pessoa.isPresent()) {
            pessoaRepository.deleteById(id); // Deleta a pessoa se encontrada
            return true; // Retorna true se a exclusão for bem-sucedida
        }
        return false; // Retorna false se a pessoa não for encontrada
    }

}
