package br.com.danielhp.controller;

import br.com.danielhp.dao.CalculoImpostoDao;
import br.com.danielhp.dao.NotaFiscalDao;
import br.com.danielhp.entitybean.CalculoImposto;
import br.com.danielhp.entitybean.Cliente;
import br.com.danielhp.entitybean.NotaFiscal;
import br.com.danielhp.mapper.CalculoImpostoMapper;
import br.com.danielhp.mapper.ClienteMapper;
import br.com.danielhp.util.Constantes;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.CalculoImpostoVo;
import br.com.danielhp.vo.InformacoesCalculoVo;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Daniel on 22/01/2017.
 */
public class CalculoImpostosController {
    private static final Logger logger = Logger.getLogger(ClienteController.class.getName());

    /**
     * Metodo responsavel por realizar os calculos dos impostos das notas fiscais no mes/ano informado
     *
     * @param informacoesCalculo
     * @return
     */
    public Info calcularImpostos(InformacoesCalculoVo informacoesCalculo) {
        Info info;

        try {
            if (verificarCalculoJaRealizadoMes(informacoesCalculo)) {
                String mensagem = "Os impostos para o mes {0} de {1} já foram calculados.";
                mensagem = MessageFormat.format(mensagem, informacoesCalculo.getMes(), informacoesCalculo.getAno().toString());
                return new Info(Info.TIPO_DANGER, mensagem, null);
            }

            if (informacoesCalculo.getCliente().getRegimeTributario().equals(Constantes.REGIME_TRIBUTARIO_SIMPLES_NACIONAL)) {
                calcularSimplesNacional(informacoesCalculo);
            } else {
                calcularLucroPresumido(informacoesCalculo);
            }

            info = new Info(Info.TIPO_SUCCESS, "Impostos calculados com sucesso!", null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
            info = new Info(Info.TIPO_DANGER, "Erro ao calcular os impostos", null);
        }
        return info;
    }

    /**
     * Realiza os calculos dos impostos para o simples nacional
     *
     * @param informacoesCalculo
     * @return
     */
    private CalculoImposto calcularSimplesNacional(InformacoesCalculoVo informacoesCalculo) {
        NotaFiscalDao notaFiscalDao = NotaFiscalDao.getInstance();
        CalculoImpostoDao calculoImpostoDao = CalculoImpostoDao.getInstance();

        BigDecimal totalAnexo1 = BigDecimal.ZERO;
        BigDecimal totalAnexo2 = BigDecimal.ZERO;
        BigDecimal totalAnexo3 = BigDecimal.ZERO;

        Cliente cliente = ClienteMapper.convertToEntity(informacoesCalculo.getCliente());

        //listo as notas do mes informado
        List<NotaFiscal> listaNotasFiscais = notaFiscalDao.list(
                informacoesCalculo.getMes(), informacoesCalculo.getAno(), cliente);

        //faco o somatorio dos totais de cada anexo
        for (NotaFiscal notaFiscal : listaNotasFiscais) {
            if (notaFiscal.getAnexo().equals(Constantes.ANEXO_COMERCIO)) {
                totalAnexo1 = totalAnexo1.add(notaFiscal.getValor());
            } else if (notaFiscal.getAnexo().equals(Constantes.ANEXO_INDUSTRIA)) {
                totalAnexo2 = totalAnexo2.add(notaFiscal.getValor());
            } else if (notaFiscal.getAnexo().equals(Constantes.ANEXO_SERVICOS)) {
                totalAnexo3 = totalAnexo3.add(notaFiscal.getValor());
            }
        }

        //calculo a porcentagem para cada anexo
        BigDecimal totalImpostosAnexo1 = totalAnexo1.multiply(Constantes.ALIQUOTA_SIMPLES_NACIONAL_ANEXO_1).divide(new BigDecimal(100));
        BigDecimal totalImpostosAnexo2 = totalAnexo2.multiply(Constantes.ALIQUOTA_SIMPLES_NACIONAL_ANEXO_2).divide(new BigDecimal(100));
        BigDecimal totalImpostosAnexo3 = totalAnexo3.multiply(Constantes.ALIQUOTA_SIMPLES_NACIONAL_ANEXO_3).divide(new BigDecimal(100));

        //soma os valores dos tres anexos
        BigDecimal totalSimplesNacional = totalImpostosAnexo1.add(totalImpostosAnexo2).add(totalImpostosAnexo3).setScale(2, BigDecimal.ROUND_HALF_UP);

        //instancio um objeto tipo CalculoImposto e persisto o mesmo
        CalculoImposto calculoImposto = new CalculoImposto();
        calculoImposto.setPago(false);
        calculoImposto.setMesAnoReferente(informacoesCalculo.getMes(), informacoesCalculo.getAno());
        calculoImposto.setTipoImposto(Constantes.TIPO_IMPOSTO_SIMPLES_NACIONAL);
        calculoImposto.setValor(totalSimplesNacional);
        calculoImposto.setCliente(cliente);

        calculoImpostoDao.persist(calculoImposto);

        return calculoImposto;
    }

    /**
     * Realiza os calculos dos impostos para o lucro presumido
     *
     * @param informacoesCalculo
     * @return
     */
    private void calcularLucroPresumido(InformacoesCalculoVo informacoesCalculo) {
        NotaFiscalDao notaFiscalDao = NotaFiscalDao.getInstance();
        CalculoImpostoDao calculoImpostoDao = CalculoImpostoDao.getInstance();

        Cliente cliente = ClienteMapper.convertToEntity(informacoesCalculo.getCliente());

        BigDecimal totalNotasFiscais = BigDecimal.ZERO;

        //listo as notas do mes informado
        List<NotaFiscal> listaNotasFiscais = notaFiscalDao.list(
                informacoesCalculo.getMes(), informacoesCalculo.getAno(), cliente);

        //Calcula o valor total de todas as notas
        for (NotaFiscal notaFiscal : listaNotasFiscais) {
            totalNotasFiscais = totalNotasFiscais.add(notaFiscal.getValor());
        }

        //Calculo o valor do imposto ISS
        BigDecimal totalISS = totalNotasFiscais.multiply(Constantes.ALIQUOTA_ISS).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

        //Crio um objeto com as informacoes do imposto
        CalculoImposto calculoImpostoISS = new CalculoImposto();
        calculoImpostoISS.setPago(false);
        calculoImpostoISS.setMesAnoReferente(informacoesCalculo.getMes(), informacoesCalculo.getAno());
        calculoImpostoISS.setTipoImposto(Constantes.TIPO_IMPOSTO_SIMPLES_ISS);
        calculoImpostoISS.setValor(totalISS);
        calculoImpostoISS.setCliente(cliente);

        //Persisto o objeto criado
        calculoImpostoDao.persist(calculoImpostoISS);

        //Calculo o valor do imposto IRPJ
        BigDecimal totalIRPJ = totalNotasFiscais.multiply(Constantes.ALIQUOTA_IRPJ).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

        //Crio um objeto com as informacoes do imposto
        CalculoImposto calculoImpostoIRPJ = new CalculoImposto();
        calculoImpostoIRPJ.setPago(false);
        calculoImpostoIRPJ.setMesAnoReferente(informacoesCalculo.getMes(), informacoesCalculo.getAno());
        calculoImpostoIRPJ.setTipoImposto(Constantes.TIPO_IMPOSTO_SIMPLES_IRPJ);
        calculoImpostoIRPJ.setValor(totalIRPJ);
        calculoImpostoIRPJ.setCliente(cliente);

        //Persisto o objeto criado
        calculoImpostoDao.persist(calculoImpostoIRPJ);

        //Calculo o valor do imposto COFINS
        BigDecimal totalCOFINS = totalNotasFiscais.multiply(Constantes.ALIQUOTA_COFINS).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

        //Crio um objeto com as informacoes do imposto
        CalculoImposto calculoImpostoCOFINS = new CalculoImposto();
        calculoImpostoCOFINS.setPago(false);
        calculoImpostoCOFINS.setMesAnoReferente(informacoesCalculo.getMes(), informacoesCalculo.getAno());
        calculoImpostoCOFINS.setTipoImposto(Constantes.TIPO_IMPOSTO_SIMPLES_COFINS);
        calculoImpostoCOFINS.setValor(totalCOFINS);
        calculoImpostoCOFINS.setCliente(cliente);

        //Persisto o objeto criado
        calculoImpostoDao.persist(calculoImpostoCOFINS);
    }

    /**
     * Lista os impostos calculados de acordo com os filtros informados
     *
     * @param filtro
     * @return
     */
    public List<CalculoImpostoVo> listarCalculosImpostos(InformacoesCalculoVo filtro) {
        List<CalculoImpostoVo> listaImpostos = null;

        try {
            CalculoImpostoDao calculoImpostoDao = CalculoImpostoDao.getInstance();

            List<CalculoImposto> lista = calculoImpostoDao.list(
                    filtro.getMes(), filtro.getAno(), ClienteMapper.convertToEntity(filtro.getCliente()));

            listaImpostos = CalculoImpostoMapper.convertToListVo(lista);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return listaImpostos;
    }

    /**
     * Altera o status de pago do imposto calculado
     *
     * @param calculoImpostoVo
     * @return
     */
    public Info alterarStatusImposto(CalculoImpostoVo calculoImpostoVo) {
        Info info;

        CalculoImpostoDao calculoImpostoDao = CalculoImpostoDao.getInstance();
        try {
            CalculoImposto calculoImposto = CalculoImpostoMapper.convertToEntity(calculoImpostoVo);

            //inverte o valor da variavel
            calculoImposto.setPago(!calculoImposto.getPago());

            //atualiza no banco de dados
            calculoImpostoDao.merge(calculoImposto);

            calculoImpostoVo = CalculoImpostoMapper.convertToVo(calculoImposto);

            info = new Info(Info.TIPO_SUCCESS, "Impostos calculados com sucesso!", calculoImpostoVo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
            info = new Info(Info.TIPO_DANGER, "Erro ao calcular os impostos", null);
        }
        return info;
    }

    /**
     * Verifica se ja for realizado os calculos dos impostos para o mes/ano informado
     *
     * @param informacoesCalculoVo
     * @return
     */
    public Boolean verificarCalculoJaRealizadoMes(InformacoesCalculoVo informacoesCalculoVo) {
        CalculoImpostoDao calculoImpostoDao = CalculoImpostoDao.getInstance();

        return calculoImpostoDao.list(informacoesCalculoVo.getMes(), informacoesCalculoVo.getAno(),
                ClienteMapper.convertToEntity(informacoesCalculoVo.getCliente())).size() > 0;
    }
}
