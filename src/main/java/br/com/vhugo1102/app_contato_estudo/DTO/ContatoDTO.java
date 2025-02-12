package br.com.vhugo1102.app_contato_estudo.DTO;

public class ContatoDTO {

	private Long id;
	private int tipoContato; // Usando o código do tipo de contato
	private String contato;
	private Long pessoaId; // Referência ao ID da Pessoa

	// Construtor
	public ContatoDTO(Long id, int tipoContato, String contato, Long pessoaId) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.pessoaId = pessoaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(int tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
	
	
}
