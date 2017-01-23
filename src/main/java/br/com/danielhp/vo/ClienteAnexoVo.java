package br.com.danielhp.vo;

import java.io.Serializable;

/**
 * Created by Daniel on 19/01/2017.
 */
public class ClienteAnexoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nomeArquivo;
    private byte[] arquivo;
    private ClienteVo cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVo cliente) {
        this.cliente = cliente;
    }
}
