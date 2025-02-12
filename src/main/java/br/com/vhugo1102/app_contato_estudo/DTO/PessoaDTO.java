package br.com.vhugo1102.app_contato_estudo.DTO;

import br.com.vhugo1102.app_contato_estudo.model.Pessoa;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String malaDireta;

	// Construtor baseado em Pessoa
	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.malaDireta = criarMalaDireta(pessoa);
	}

	// Método auxiliar para criar a mala direta
	private String criarMalaDireta(Pessoa pessoa) {
		return pessoa.getEndereco() + ", " + pessoa.getCep() + " – " + pessoa.getCidade() + "/" + pessoa.getUf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMalaDireta() {
		return malaDireta;
	}

	public void setMalaDireta(String malaDireta) {
		this.malaDireta = malaDireta;
	}

}
