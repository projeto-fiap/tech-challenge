package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

class UpdateItemUseCaseImplTest {

	@Mock
	private ItemDataProvider itemDataProvider;

	@InjectMocks
	private UpdateItemUseCaseImpl updateItemUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_updatesItemSuccessfully() {
		Item existingItem = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");
		Item updatedItem = new Item(1L, "Hamburguer Vegano", BigDecimal.valueOf(50.0), BigDecimal.valueOf(1), "unit",
				ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne vegano", "https://www.google.com");

		when(itemDataProvider.retrieveById(1L)).thenReturn(Optional.of(existingItem));
		when(itemDataProvider.saveAll(List.of(updatedItem))).thenReturn(List.of(updatedItem));

		Item result = updateItemUseCaseImpl.execute(1L, updatedItem);

		assertEquals(updatedItem.getName(), result.getName());
		assertEquals(updatedItem.getPrice(), result.getPrice());
	}

}