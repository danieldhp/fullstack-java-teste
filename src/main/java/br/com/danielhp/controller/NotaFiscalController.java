package br.com.danielhp.controller;

import br.com.danielhp.dao.NotaFiscalDao;
import br.com.danielhp.entitybean.NotaFiscal;
import br.com.danielhp.mapper.NotaFiscalMapper;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.NotaFiscalVo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Daniel on 20/01/2017.
 */
public class NotaFiscalController {
    private static final Logger logger = Logger.getLogger(NotaFiscalController.class.getName());

    /**
     * Persiste ou atualiza o objeto do tipo NotaFiscal
     *
     * @param notaFiscalVo
     * @return
     */
    public Info salvarNotaFiscal(NotaFiscalVo notaFiscalVo) {
        Info info;

        try {
            NotaFiscal notaFiscal = NotaFiscalMapper.convertToEntity(notaFiscalVo);

            NotaFiscalDao notaFiscalDao = NotaFiscalDao.getInstance();
            if (notaFiscal.getId() != null && notaFiscal.getId() > 0) {
                notaFiscalDao.merge(notaFiscal);
            } else {
                notaFiscalDao.persist(notaFiscal);
            }

            notaFiscalVo = NotaFiscalMapper.convertToVo(notaFiscal);

            info = new Info(Info.TIPO_SUCCESS, "Salvo com sucesso", notaFiscalVo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
            info = new Info(Info.TIPO_DANGER, "Erro ao salvar", null);
        }
        return info;
    }

    /**
     * Lista as notasFiscais salvas
     *
     * @return
     */
    public List<NotaFiscalVo> listarNotasFiscais() {
        List<NotaFiscalVo> listaNotasFiscais = null;

        try {
            NotaFiscalDao notaFiscalDao = NotaFiscalDao.getInstance();
            List<NotaFiscal> lista = notaFiscalDao.findAll();

            listaNotasFiscais = NotaFiscalMapper.convertToListVo(lista);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return listaNotasFiscais;
    }
}
