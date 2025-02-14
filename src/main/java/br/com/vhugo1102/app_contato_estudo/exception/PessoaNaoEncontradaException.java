package br.com.vhugo1102.app_contato_estudo.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
   private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
