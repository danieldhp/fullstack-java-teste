package br.com.danielhp.util;

import java.math.BigDecimal;

/**
 * Created by Daniel on 22/01/2017.
 */
public class Constantes {

    //Constantes sobre regime tributario do cliente
    public static final String REGIME_TRIBUTARIO_SIMPLES_NACIONAL = "SN";
    public static final String REGIME_TRIBUTARIO_LUCRO_PRESUMIDO = "LP";

    //Constantes sobre anexos da nota fiscal
    public static final String ANEXO_COMERCIO = "1";
    public static final String ANEXO_INDUSTRIA = "2";
    public static final String ANEXO_SERVICOS = "3";

    //Constantes sobre tipos de impostos
    public static final String TIPO_IMPOSTO_SIMPLES_NACIONAL = "SN";
    public static final String TIPO_IMPOSTO_SIMPLES_IRPJ = "IRPJ";
    public static final String TIPO_IMPOSTO_SIMPLES_ISS = "ISS";
    public static final String TIPO_IMPOSTO_SIMPLES_COFINS = "COFINS";

    //Constantes sobre aliquota dos impostos
    public static final BigDecimal ALIQUOTA_SIMPLES_NACIONAL_ANEXO_1 = new BigDecimal("6");
    public static final BigDecimal ALIQUOTA_SIMPLES_NACIONAL_ANEXO_2 = new BigDecimal("8.5");
    public static final BigDecimal ALIQUOTA_SIMPLES_NACIONAL_ANEXO_3 = new BigDecimal("11");
    public static final BigDecimal ALIQUOTA_IRPJ = new BigDecimal("4.8");
    public static final BigDecimal ALIQUOTA_ISS = new BigDecimal("2");
    public static final BigDecimal ALIQUOTA_COFINS = new BigDecimal("3");
}
