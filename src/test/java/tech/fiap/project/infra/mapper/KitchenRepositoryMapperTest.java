package tech.fiap.project.infra.mapper;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.infra.entity.KitchenEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class KitchenRepositoryMapperTest {

	@Test
	void toEntity_shouldMapKitchenToKitchenEntity() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		KitchenEntity kitchenEntity = KitchenRepositoryMapper.toEntity(kitchen);

		assertEquals(kitchen.getOrderId(), kitchenEntity.getOrderId());
		assertEquals(kitchen.getCreationDate(), kitchenEntity.getCreationDate());
		assertEquals(kitchen.getUpdatedDate(), kitchenEntity.getUpdatedDate());
		assertEquals(kitchen.getStatus(), kitchenEntity.getStatus());
	}

	@Test
	void toEntity_shouldReturnNullWhenKitchenIsNull() {
		KitchenEntity kitchenEntity = KitchenRepositoryMapper.toEntity(null);
		assertNull(kitchenEntity);
	}

	@Test
	void toDomain_shouldMapKitchenEntityToKitchen() {
		KitchenEntity kitchenEntity = new KitchenEntity();
		kitchenEntity.setOrderId(1L);
		kitchenEntity.setCreationDate(LocalDateTime.now());
		kitchenEntity.setUpdatedDate(LocalDateTime.now());
		kitchenEntity.setStatus(KitchenStatus.IN_PRODUCTION);

		Kitchen kitchen = KitchenRepositoryMapper.toDomain(kitchenEntity);

		assertEquals(kitchenEntity.getOrderId(), kitchen.getOrderId());
		assertEquals(kitchenEntity.getCreationDate(), kitchen.getCreationDate());
		assertEquals(kitchenEntity.getUpdatedDate(), kitchen.getUpdatedDate());
		assertEquals(kitchenEntity.getStatus(), kitchen.getStatus());
	}

	@Test
	void toDomain_shouldReturnNullWhenKitchenEntityIsNull() {
		Kitchen kitchen = KitchenRepositoryMapper.toDomain((KitchenEntity) null);
		assertNull(kitchen);
	}

	@Test
	void toDomain_shouldMapListOfKitchenEntitiesToListOfKitchens() {
		KitchenEntity kitchenEntity1 = new KitchenEntity();
		kitchenEntity1.setOrderId(1L);
		kitchenEntity1.setCreationDate(LocalDateTime.now());
		kitchenEntity1.setUpdatedDate(LocalDateTime.now());
		kitchenEntity1.setStatus(KitchenStatus.DONE);

		KitchenEntity kitchenEntity2 = new KitchenEntity();
		kitchenEntity2.setOrderId(2L);
		kitchenEntity2.setCreationDate(LocalDateTime.now());
		kitchenEntity2.setUpdatedDate(LocalDateTime.now());
		kitchenEntity2.setStatus(KitchenStatus.DONE);
		List<KitchenEntity> kitchenEntities = List.of(kitchenEntity1, kitchenEntity2);
		List<Kitchen> kitchens = KitchenRepositoryMapper.toDomain(kitchenEntities);

		assertEquals(kitchenEntities.size(), kitchens.size());
		for (int i = 0; i < kitchenEntities.size(); i++) {
			assertEquals(kitchenEntities.get(i).getOrderId(), kitchens.get(i).getOrderId());
			assertEquals(kitchenEntities.get(i).getCreationDate(), kitchens.get(i).getCreationDate());
			assertEquals(kitchenEntities.get(i).getUpdatedDate(), kitchens.get(i).getUpdatedDate());
			assertEquals(kitchenEntities.get(i).getStatus(), kitchens.get(i).getStatus());
		}
	}

}