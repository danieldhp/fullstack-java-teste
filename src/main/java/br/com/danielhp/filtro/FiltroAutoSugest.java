package br.com.danielhp.filtro;

import java.io.Serializable;

/**
 * Created by Daniel on 22/01/2017.
 */
public class FiltroAutoSugest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pesquisa;

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
}
