package br.com.danielhp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Daniel on 19/01/2017.
 */
public class CalculoImpostoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String tipoImposto;
    private BigDecimal valor;
    private String mesAnoReferente;
    private Boolean pago;
    private ClienteVo cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoImposto() {
        return tipoImposto;
    }

    public void setTipoImposto(String tipoImposto) {
        this.tipoImposto = tipoImposto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMesAnoReferente() {
        return mesAnoReferente;
    }

    public void setMesAnoReferente(String mesAnoReferente) {
        this.mesAnoReferente = mesAnoReferente;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVo cliente) {
        this.cliente = cliente;
    }
}
