package br.com.danielhp.mapper;

import br.com.danielhp.entitybean.NotaFiscal;
import br.com.danielhp.vo.NotaFiscalVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 19/01/2017.
 */
public class NotaFiscalMapper {

    /**
     * Convert a entity para Vo
     *
     * @param entity
     * @return
     */
    public static NotaFiscalVo convertToVo(NotaFiscal entity) {
        NotaFiscalVo vo = null;

        if (entity != null) {
            vo = new NotaFiscalVo();
            vo.setId(entity.getId());
            vo.setValor(entity.getValor());
            vo.setData(entity.getData());
            vo.setDescricao(entity.getDescricao());
            vo.setNumero(entity.getNumero());
            vo.setAnexo(entity.getAnexo());
            vo.setCliente(ClienteMapper.convertToVo(entity.getCliente()));
        }
        return vo;
    }

    /**
     * Convert uma lista de entities para uma lista de VOs
     *
     * @param listEntity
     * @return
     */
    public static List<NotaFiscalVo> convertToListVo(List<NotaFiscal> listEntity) {
        List<NotaFiscalVo> listVo = null;

        if (listEntity != null) {
            listVo = new ArrayList<>();
            for (NotaFiscal entity : listEntity) {
                listVo.add(convertToVo(entity));
            }
        }

        return listVo;
    }

    /**
     * Convert o vo para entity
     *
     * @param vo
     * @return
     */
    public static NotaFiscal convertToEntity(NotaFiscalVo vo) {
        NotaFiscal entity = null;

        if (vo != null) {
            entity = new NotaFiscal();
            entity.setId(vo.getId());
            entity.setValor(vo.getValor());
            entity.setData(vo.getData());
            entity.setDescricao(vo.getDescricao());
            entity.setNumero(vo.getNumero());
            entity.setAnexo(vo.getAnexo());
            entity.setCliente(ClienteMapper.convertToEntity(vo.getCliente()));
        }
        return entity;
    }

    /**
     * Convert uma lista de VOs para uma lista de Entities
     *
     * @param listVo
     * @return
     */
    public static List<NotaFiscal> convertToListEntity(List<NotaFiscalVo> listVo) {
        List<NotaFiscal> listEntity = null;

        if (listVo != null) {
            listEntity = new ArrayList<>();
            for (NotaFiscalVo vo : listVo) {
                listEntity.add(convertToEntity(vo));
            }
        }
        return listEntity;
    }
}
