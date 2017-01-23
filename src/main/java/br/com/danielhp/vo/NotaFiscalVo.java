package br.com.danielhp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Daniel on 19/01/2017.
 */
public class NotaFiscalVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer numero;
    private Date data;
    private String descricao;
    private BigDecimal valor;
    private ClienteVo cliente;
    private String anexo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        if (data.getClass().equals(java.sql.Date.class)) {
            data = new Date(data.getTime());
        }

        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVo cliente) {
        this.cliente = cliente;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }
}
