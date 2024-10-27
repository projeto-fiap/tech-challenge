package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.infra.entity.KitchenEntity;

import java.util.List;

public class KitchenMapper {

	public static List<KitchenDTO> toDTO(List<Kitchen> kitchens) {
		return kitchens.stream().map(KitchenMapper::toDTO).toList();
	}

	public static KitchenDTO toDTO(Kitchen kitchen) {
		if (kitchen == null) {
			return null;
		}
		else {
			KitchenDTO kitchenDTO = new KitchenDTO();
			kitchenDTO.setOrderId(kitchen.getOrderId());
			kitchenDTO.setCreationDate(kitchen.getCreationDate());
			kitchenDTO.setUpdatedDate(kitchen.getUpdatedDate());
			kitchenDTO.setStatus(kitchen.getStatus());
			return kitchenDTO;
		}
	}

	public static Kitchen toDomain(KitchenDTO kitchen) {
		if (kitchen == null) {
			return null;
		}
		else {
			return new Kitchen(kitchen.getOrderId(), kitchen.getCreationDate(), kitchen.getUpdatedDate(), kitchen.getStatus());
		}
	}

	public static Kitchen toDomain(KitchenEntity kitchen) {
		if (kitchen == null) {
			return null;
		}
		else {
			return new Kitchen(kitchen.getOrderId(), kitchen.getCreationDate(), kitchen.getUpdatedDate(), kitchen.getStatus());
		}
	}

	public static KitchenEntity toEntity(Kitchen kitchen) {
		if (kitchen == null) {
			return null;
		}
		else {
			KitchenEntity kitchenEntity = new KitchenEntity();
			kitchenEntity.setOrderId(kitchen.getOrderId());
			kitchenEntity.setCreationDate(kitchen.getCreationDate());
			kitchenEntity.setStatus(kitchen.getStatus());
			return kitchenEntity;
		}
	}
}
