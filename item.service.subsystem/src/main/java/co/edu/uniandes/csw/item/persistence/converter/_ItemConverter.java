
package co.edu.uniandes.csw.item.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;

public abstract class _ItemConverter {


	public static ItemDTO entity2PersistenceDTO(ItemEntity entity){
		if (entity != null) {
			ItemDTO dto = new ItemDTO();
				dto.setId(entity.getId());
				dto.setCantidad(entity.getCantidad());
				dto.setName(entity.getName());
				dto.setProductsId(entity.getProductsId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ItemEntity persistenceDTO2Entity(ItemDTO dto){
		if(dto!=null){
			ItemEntity entity=new ItemEntity();
			entity.setId(dto.getId());
			entity.setCantidad(dto.getCantidad());
			entity.setName(dto.getName());
			entity.setProductsId(dto.getProductsId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ItemDTO> entity2PersistenceDTOList(List<ItemEntity> entities){
		List<ItemDTO> dtos=new ArrayList<ItemDTO>();
		for(ItemEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ItemEntity> persistenceDTO2EntityList(List<ItemDTO> dtos){
		List<ItemEntity> entities=new ArrayList<ItemEntity>();
		for(ItemDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}