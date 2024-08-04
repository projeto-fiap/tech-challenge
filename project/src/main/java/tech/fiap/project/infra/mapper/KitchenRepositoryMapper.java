package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.Payment;
import tech.fiap.project.infra.entity.KitchenEntity;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.PaymentEntity;

import java.util.List;

public class KitchenRepositoryMapper {

	public static List<Kitchen> toDomain(List<KitchenEntity> kitchen) {
		return kitchen.stream().map(KitchenRepositoryMapper::toDomain).toList();
	}

	public static KitchenEntity toEntity(Kitchen kitchen) {
		if (kitchen == null) {
			return null;
		}
		else {
			KitchenEntity kitchenEntity = new KitchenEntity();
			kitchenEntity.setCreationDate(kitchen.getCreationDate());
			kitchenEntity.setStatus(kitchen.getStatus());
			kitchenEntity.setOrderId(kitchen.getOrderId());
			return kitchenEntity;
		}
	}

	public static Kitchen toDomain(KitchenEntity kitchenEntity) {
		if (kitchenEntity == null) {
			return null;
		}
		else {
			return new Kitchen(kitchenEntity.getOrderId(), kitchenEntity.getCreationDate(), kitchenEntity.getStatus());
		}
	}

}
