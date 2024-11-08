package tech.fiap.project.app.adapter;

import org.junit.jupiter.api.Test;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.infra.entity.KitchenEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KitchenMapperTest {

	@Test
	void toDTO_shouldMapKitchenToKitchenDTO() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);

		KitchenDTO kitchenDTO = KitchenMapper.toDTO(kitchen);

		assertNotNull(kitchenDTO);
		assertEquals(kitchen.getOrderId(), kitchenDTO.getOrderId());
		assertEquals(kitchen.getCreationDate(), kitchenDTO.getCreationDate());
		assertEquals(kitchen.getUpdatedDate(), kitchenDTO.getUpdatedDate());
		assertEquals(kitchen.getStatus(), kitchenDTO.getStatus());
	}

	@Test
	void toDTO_shouldReturnNullWhenKitchenIsNull() {
		KitchenDTO kitchenDTO = KitchenMapper.toDTO((Kitchen) null);

		assertNull(kitchenDTO);
	}

	@Test
	void toDTOList_shouldMapKitchenListToKitchenDTOList() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		List<Kitchen> kitchens = List.of(kitchen, kitchen);

		List<KitchenDTO> kitchenDTOs = KitchenMapper.toDTO(kitchens);

		assertNotNull(kitchenDTOs);
		assertEquals(kitchens.size(), kitchenDTOs.size());
		assertEquals(kitchen.getOrderId(), kitchenDTOs.get(0).getOrderId());
	}

	@Test
	void toDomain_shouldMapKitchenDTOToKitchen() {
		KitchenDTO kitchenDTO = new KitchenDTO();
		kitchenDTO.setOrderId(1L);
		kitchenDTO.setCreationDate(LocalDateTime.now());
		kitchenDTO.setUpdatedDate(LocalDateTime.now());
		kitchenDTO.setStatus(KitchenStatus.IN_PRODUCTION);

		Kitchen kitchen = KitchenMapper.toDomain(kitchenDTO);

		assertNotNull(kitchen);
		assertEquals(kitchenDTO.getOrderId(), kitchen.getOrderId());
		assertEquals(kitchenDTO.getCreationDate(), kitchen.getCreationDate());
		assertEquals(kitchenDTO.getUpdatedDate(), kitchen.getUpdatedDate());
		assertEquals(kitchenDTO.getStatus(), kitchen.getStatus());
	}

	@Test
	void toDomain_shouldReturnNullWhenKitchenDTOIsNull() {
		Kitchen kitchen = KitchenMapper.toDomain((KitchenDTO) null);

		assertNull(kitchen);
	}

	@Test
	void toDomain_shouldMapKitchenEntityToKitchen() {
		KitchenEntity kitchenEntity = new KitchenEntity();
		kitchenEntity.setOrderId(1L);
		kitchenEntity.setCreationDate(LocalDateTime.now());
		kitchenEntity.setUpdatedDate(LocalDateTime.now());
		kitchenEntity.setStatus(KitchenStatus.IN_PRODUCTION);

		Kitchen kitchen = KitchenMapper.toDomain(kitchenEntity);

		assertNotNull(kitchen);
		assertEquals(kitchenEntity.getOrderId(), kitchen.getOrderId());
		assertEquals(kitchenEntity.getCreationDate(), kitchen.getCreationDate());
		assertEquals(kitchenEntity.getUpdatedDate(), kitchen.getUpdatedDate());
		assertEquals(kitchenEntity.getStatus(), kitchen.getStatus());
	}

	@Test
	void toDomain_shouldReturnNullWhenKitchenEntityIsNull() {
		Kitchen kitchen = KitchenMapper.toDomain((KitchenEntity) null);

		assertNull(kitchen);
	}

	@Test
	void toEntity_shouldMapKitchenToKitchenEntity() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);

		KitchenEntity kitchenEntity = KitchenMapper.toEntity(kitchen);

		assertNotNull(kitchenEntity);
		assertEquals(kitchen.getOrderId(), kitchenEntity.getOrderId());
		assertEquals(kitchen.getCreationDate(), kitchenEntity.getCreationDate());
		assertEquals(kitchen.getStatus(), kitchenEntity.getStatus());
	}

	@Test
	void toEntity_shouldReturnNullWhenKitchenIsNull() {
		KitchenEntity kitchenEntity = KitchenMapper.toEntity((Kitchen) null);

		assertNull(kitchenEntity);
	}

}