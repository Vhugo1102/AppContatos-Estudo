package br.com.vhugo1102.app_contato_estudo.DTO;

import br.com.vhugo1102.app_contato_estudo.enums.TipoContato;
import br.com.vhugo1102.app_contato_estudo.model.Contato;

public class ContatoDTO {

	private Long id;
    private TipoContato tipoContato;
    private String contato;
    private Long idPessoa;
    private PessoaDTO pessoaDTO; // Nova propriedade para PessoaDTO

    // Construtor vazio para o DTO
    public ContatoDTO() {}

 // Construtor com Contato e PessoaDTO
    public ContatoDTO(Contato contato, PessoaDTO pessoaDTO) {
        this.id = contato.getId();
        this.tipoContato = contato.getTipoContato();
        this.contato = contato.getContato();
        this.idPessoa = contato.getPessoa().getId();  // Pegando o ID da pessoa
        this.pessoaDTO = pessoaDTO; // Atribuindo PessoaDTO
    }

    // Construtor com os dados diretamente
    public ContatoDTO(Long id, TipoContato tipoContato, String contato, Long idPessoa) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.idPessoa = idPessoa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    public PessoaDTO getPessoaDTO() {
		return pessoaDTO;
	}

	public void setPessoaDTO(PessoaDTO pessoaDTO) {
		this.pessoaDTO = pessoaDTO;
	}

	@Override
    public String toString() {
        return "ContatoDTO [id=" + id + ", tipoContato=" + tipoContato + ", contato=" + contato
                + ", idPessoa=" + idPessoa + "]";
    }
}
