package br.com.vhugo1102.app_contato_estudo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vhugo1102.app_contato_estudo.DTO.ContatoDTO;
import br.com.vhugo1102.app_contato_estudo.DTO.PessoaDTO;
import br.com.vhugo1102.app_contato_estudo.model.Contato;
import br.com.vhugo1102.app_contato_estudo.model.Pessoa;
import br.com.vhugo1102.app_contato_estudo.repository.ContatoRepository;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    
    // CRUD - CREATE
    public ContatoDTO save(Contato contato) {
        // Regra de negócio: verificar se os campos obrigatórios estão preenchidos
        if (contato.getTipoContato() == null || contato.getContato() == null || contato.getPessoa() == null) {
            System.out.println("Dados do contato incompletos.");
            return null;
        }
        
        // Salvar o contato no banco de dados
        try {
            Contato savedContato = contatoRepository.save(contato);
            Pessoa pessoa = savedContato.getPessoa();
            // Criar o DTO com a pessoa e o contato
            ContatoDTO contatoDTO = new ContatoDTO(savedContato, new PessoaDTO(pessoa));
            return contatoDTO;
        } catch (Exception e) {
            System.out.println("Erro ao inserir contato: " + e.getMessage());
            return null;
        }
    }

    // CRUD - READ (Leitura individual ou lista)
    public ContatoDTO findById(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        if (contato.isPresent()) {
            Pessoa pessoa = contato.get().getPessoa();
            return new ContatoDTO(contato.get(), new PessoaDTO(pessoa));
        }
        return null;
    }

    public List<ContatoDTO> findAll() {
        List<Contato> contatos = contatoRepository.findAll();
        // Converter cada Contato para ContatoDTO
        return contatos.stream()
                .map(contato -> new ContatoDTO(contato, new PessoaDTO(contato.getPessoa())))
                .collect(Collectors.toList());
    }

    // CRUD - UPDATE
    public ContatoDTO update(Contato contato) {
        // Verificar se o contato existe
        Optional<Contato> findContato = contatoRepository.findById(contato.getId());
        if (findContato.isPresent()) {
            Contato existingContato = findContato.get();
            existingContato.setTipoContato(contato.getTipoContato());
            existingContato.setContato(contato.getContato());
            existingContato.setPessoa(contato.getPessoa());
            Contato updatedContato = contatoRepository.save(existingContato);
            Pessoa pessoa = updatedContato.getPessoa();
            return new ContatoDTO(updatedContato, new PessoaDTO(pessoa));
        }
        return null;
    }

    // CRUD - DELETE
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }
}
