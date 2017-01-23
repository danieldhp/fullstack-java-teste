package br.com.danielhp.entitybean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Daniel on 19/01/2017.
 */
@Entity
@Table(name = "CALCULO_IMPOSTO")
public class CalculoImposto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TIPO_IMPOSTO", length = 6)
    private String tipoImposto;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "MES_ANO_REFERENTE", length = 10)
    private String mesAnoReferente;

    @Column(name = "PAGO")
    private Boolean pago;

    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Cliente cliente;

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

    public void setMesAnoReferente(Integer mes, Integer ano) {
        if (mes < 10) {
            this.mesAnoReferente = "0" + mes + "/" + ano;
        } else {
            this.mesAnoReferente = mes + "/" + ano;
        }
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CalculoImposto)) {
            return false;
        }
        CalculoImposto other = (CalculoImposto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id
                .equals(other.id)));
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[id=" + id + "]";
    }
}
