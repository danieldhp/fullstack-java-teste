package br.com.danielhp.mapper;

import br.com.danielhp.entitybean.Cliente;
import br.com.danielhp.vo.ClienteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 19/01/2017.
 */
public class ClienteMapper {

    /**
     * Convert a entity para Vo
     *
     * @param entity
     * @return
     */
    public static ClienteVo convertToVo(Cliente entity) {
        ClienteVo vo = null;

        if (entity != null) {
            vo = new ClienteVo();
            vo.setId(entity.getId());
            vo.setCnpj(entity.getCnpj());
            vo.setEmail(entity.getEmail());
            vo.setRazaoSocial(entity.getRazaoSocial());
            vo.setRegimeTributario(entity.getRegimeTributario());
            vo.setAnexo(entity.getAnexo());
        }
        return vo;
    }

    /**
     * Convert uma lista de entities para uma lista de VOs
     *
     * @param listEntity
     * @return
     */
    public static List<ClienteVo> convertToListVo(List<Cliente> listEntity) {
        List<ClienteVo> listVo = null;

        if (listEntity != null) {
            listVo = new ArrayList<>();
            for (Cliente entity : listEntity) {
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
    public static Cliente convertToEntity(ClienteVo vo) {
        Cliente entity = null;

        if (vo != null) {
            entity = new Cliente();
            entity.setId(vo.getId());
            entity.setCnpj(vo.getCnpj());
            entity.setEmail(vo.getEmail());
            entity.setRazaoSocial(vo.getRazaoSocial());
            entity.setRegimeTributario(vo.getRegimeTributario());
            entity.setAnexo(vo.getAnexo());
        }
        return entity;
    }

    /**
     * Convert uma lista de VOs para uma lista de Entities
     *
     * @param listVo
     * @return
     */
    public static List<Cliente> convertToListEntity(List<ClienteVo> listVo) {
        List<Cliente> listEntity = null;

        if (listVo != null) {
            listEntity = new ArrayList<>();
            for (ClienteVo vo : listVo) {
                listEntity.add(convertToEntity(vo));
            }
        }
        return listEntity;
    }
}
