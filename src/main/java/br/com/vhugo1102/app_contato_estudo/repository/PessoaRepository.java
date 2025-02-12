package br.com.vhugo1102.app_contato_estudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vhugo1102.app_contato_estudo.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
