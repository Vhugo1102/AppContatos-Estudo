package br.com.vhugo1102.app_contato_estudo.enums;

public enum TipoContato {
    TELEFONE(0),
    CELULAR(1);

    private final int codigo;

    TipoContato(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoContato fromCodigo(int codigo) {
        for (TipoContato tipo : TipoContato.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}

