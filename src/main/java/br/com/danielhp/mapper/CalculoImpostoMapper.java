package br.com.danielhp.mapper;

import br.com.danielhp.entitybean.CalculoImposto;
import br.com.danielhp.vo.CalculoImpostoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 19/01/2017.
 */
public class CalculoImpostoMapper {

    /**
     * Convert a entity para Vo
     *
     * @param entity
     * @return
     */
    public static CalculoImpostoVo convertToVo(CalculoImposto entity) {
        CalculoImpostoVo vo = null;

        if (entity != null) {
            vo = new CalculoImpostoVo();
            vo.setId(entity.getId());
            vo.setMesAnoReferente(entity.getMesAnoReferente());
            vo.setPago(entity.getPago());
            vo.setTipoImposto(entity.getTipoImposto());
            vo.setValor(entity.getValor());
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
    public static List<CalculoImpostoVo> convertToListVo(List<CalculoImposto> listEntity) {
        List<CalculoImpostoVo> listVo = null;

        if (listEntity != null) {
            listVo = new ArrayList<>();
            for (CalculoImposto entity : listEntity) {
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
    public static CalculoImposto convertToEntity(CalculoImpostoVo vo) {
        CalculoImposto entity = null;

        if (vo != null) {
            entity = new CalculoImposto();
            entity.setId(vo.getId());
            entity.setMesAnoReferente(vo.getMesAnoReferente());
            entity.setPago(vo.getPago());
            entity.setTipoImposto(vo.getTipoImposto());
            entity.setValor(vo.getValor());
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
    public static List<CalculoImposto> convertToListEntity(List<CalculoImpostoVo> listVo) {
        List<CalculoImposto> listEntity = null;

        if (listVo != null) {
            listEntity = new ArrayList<>();
            for (CalculoImpostoVo vo : listVo) {
                listEntity.add(convertToEntity(vo));
            }
        }
        return listEntity;
    }
}
