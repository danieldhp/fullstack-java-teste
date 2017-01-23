package br.com.danielhp.entitybean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Daniel on 19/01/2017.
 */
@Entity
@Table(name = "NOTA_FISCAL")
public class NotaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "DESCRICAO", length = 2)
    private String descricao;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Cliente cliente;

    @Column(name = "ANEXO", length = 2)
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id
                .equals(other.id)));
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[id=" + id + "]";
    }
}
