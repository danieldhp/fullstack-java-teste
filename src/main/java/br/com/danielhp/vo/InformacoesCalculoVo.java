package br.com.danielhp.vo;

import java.io.Serializable;

/**
 * Created by Daniel on 22/01/2017.
 */
public class InformacoesCalculoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mes;
    private Integer ano;
    private ClienteVo cliente;

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVo cliente) {
        this.cliente = cliente;
    }
}
