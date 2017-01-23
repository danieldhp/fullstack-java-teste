package br.com.danielhp.util;

import java.io.Serializable;

/**
 * Created by Daniel on 20/01/2017.
 */
public class Info implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TIPO_WARNING = "warning";
    public static final String TIPO_DANGER = "danger";
    public static final String TIPO_SUCCESS = "success";

    private String tipo;
    private String mensagem;
    private Object objeto;

    public Info(){

    }

    public Info(String tipo, String mensagem, Object objeto) {
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.objeto = objeto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
