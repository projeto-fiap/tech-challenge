package tech.fiap.project.infra.dataprovider;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.mapper.ItemRepositoryMapper;
import tech.fiap.project.infra.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemDataProviderImplTest {

	@Mock
	private ItemRepository itemRepository;

	@InjectMocks
	private ItemDataProviderImpl itemDataProvider;

	private Item item1 = new Item(1L, "Hamburguer", BigDecimal.valueOf(45.5), BigDecimal.valueOf(1), "unit",
			ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne", "https://www.google.com");

	private Item item2 = new Item(1L, "Hamburguer Vegano", BigDecimal.valueOf(50.0), BigDecimal.valueOf(1), "unit",
			ItemCategory.FOOD, new ArrayList<>(), "Hamburguer de carne vegano", "https://www.google.com");

	public ItemDataProviderImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveAll_shouldReturnListOfItems() {
		List<ItemEntity> itemEntities = List.of(new ItemEntity(), new ItemEntity());
		when(itemRepository.findAll()).thenReturn(itemEntities);

		List<Item> items = itemDataProvider.retrieveAll();

		assertEquals(itemEntities.size(), items.size());
		verify(itemRepository, times(1)).findAll();
	}

	@Test
	void retrieveById_shouldReturnItem() {
		ItemEntity itemEntity = new ItemEntity();
		when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));

		Optional<Item> item = itemDataProvider.retrieveById(1L);

		assertTrue(item.isPresent());
		verify(itemRepository, times(1)).findById(1L);
	}

	@Test
	void saveAll_shouldSaveAndReturnListOfItems() {

		List<Item> items = List.of(item1, item2);
		List<ItemEntity> itemEntities = List.of(new ItemEntity(), new ItemEntity());
		when(itemRepository.saveAll(any())).thenReturn(itemEntities);

		List<Item> savedItems = itemDataProvider.saveAll(items);

		assertEquals(itemEntities.size(), savedItems.size());
		verify(itemRepository, times(1)).saveAll(any());
	}

	@Test
	void save_shouldSaveAndReturnItem() {
		ItemEntity itemEntity = new ItemEntity();
		when(itemRepository.save(any())).thenReturn(itemEntity);

		Item savedItem = itemDataProvider.save(item1);

		assertEquals(itemEntity.getId(), savedItem.getId());
		verify(itemRepository, times(1)).save(any());
	}

	@Test
	void deleteById_shouldDeleteItemById() {
		doNothing().when(itemRepository).deleteById(1L);

		itemDataProvider.deleteById(1L);

		verify(itemRepository, times(1)).deleteById(1L);
	}

	@Test
	void retrieveByCategory_shouldReturnListOfItems() {
		ItemCategory category = ItemCategory.FOOD;
		List<ItemEntity> itemEntities = List.of(new ItemEntity(), new ItemEntity());
		when(itemRepository.findByCategory(category)).thenReturn(itemEntities);

		List<Item> items = itemDataProvider.retrieveByCategory(category);

		assertEquals(itemEntities.size(), items.size());
		verify(itemRepository, times(1)).findByCategory(category);
	}

}