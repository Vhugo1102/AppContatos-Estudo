package br.com.vhugo1102.app_contato_estudo.DTO;

import br.com.vhugo1102.app_contato_estudo.enums.TipoContato;
import br.com.vhugo1102.app_contato_estudo.model.Contato;

public class ContatoDTO {

    private Long id;
    private TipoContato tipoContato;  // Usando enum TipoContato
    private String contato;
    private PessoaDTO pessoa;  // Retornando PessoaDTO completo

    public ContatoDTO() {}

    public ContatoDTO(Contato contato, PessoaDTO pessoa) {
        this.id = contato.getId();
        this.tipoContato = contato.getTipoContato();
        this.contato = contato.getContato();
        this.pessoa = pessoa;  // PessoaDTO completo
    }

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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "ContatoDTO [id=" + id + ", tipoContato=" + tipoContato + ", contato=" + contato
                + ", pessoa=" + pessoa + "]";
    }
}
