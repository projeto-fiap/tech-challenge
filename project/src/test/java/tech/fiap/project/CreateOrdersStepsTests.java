package tech.fiap.project;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.controller.OrderController;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.dto.ItemRequestDTO;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ActiveProfiles("test")
@SpringBootTest
class CreateOrdersStepsTests {

	@Autowired
	OrderController orderController;

	@Test
	void createOrderAllSteps() {
		ResponseEntity<OrderResponseDTO> foodStep = createOrder();
		// ResponseEntity<OrderResponseDTO> foodAccompanimentCreated =
		// updateOrder(foodStep,
		// createFoodAccompanimentItens());
		// ResponseEntity<OrderResponseDTO> drinkOrderCreated =
		// updateOrder(foodAccompanimentCreated, createDrinkItems());
		// ResponseEntity<OrderResponseDTO> dessertOrderCreated =
		// updateOrder(drinkOrderCreated, createDessertItems());
		// Assertions.assertEquals(Objects.requireNonNull(dessertOrderCreated.getBody()).getId(),
		// Objects.requireNonNull(foodStep.getBody()).getId());
	}

	private ResponseEntity<OrderResponseDTO> updateOrder(ResponseEntity<OrderRequestDTO> orderCreated,
			List<ItemDTO> newItens) {
		OrderRequestDTO body = orderCreated.getBody();
		List<ItemRequestDTO> orderItems = Objects.requireNonNull(body).getItems();
		ArrayList newOrderItens = new ArrayList<OrderRequestDTO>();
		newOrderItens.addAll(orderItems);
		newOrderItens.addAll(newItens);
		body.setItems(newOrderItens);
		ResponseEntity<OrderResponseDTO> orderUpdated = createOrUpdateOrder(body);
		validateReturn(orderUpdated);
		return orderUpdated;
	}

	private ResponseEntity<OrderResponseDTO> createOrder() {
		OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
		orderRequestDTO.setItems(createFoodItems());
		ResponseEntity<OrderResponseDTO> foodOrderCreated = createOrUpdateOrder(orderRequestDTO);
		validateReturn(foodOrderCreated);
		return foodOrderCreated;
	}

	private void validateReturn(ResponseEntity<OrderResponseDTO> orderCreated) {
		OrderResponseDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		Assertions.assertNull(body.getPerson());
	}

	private List<ItemRequestDTO> createFoodItems() {
		List<ItemRequestDTO> itemDTOS = new ArrayList<>();
		ItemRequestDTO item = new ItemRequestDTO();
		item.setId(1L);
		item.setUnit("unit");
		item.setQuantity(BigDecimal.ONE);
		itemDTOS.add(item);
		return itemDTOS;
	}

	private List<ItemDTO> createFoodAccompanimentItens() {
		List<ItemDTO> itemDTOS = new ArrayList<>();
		ItemDTO fries = new ItemDTO();
		fries.setCategory(ItemCategory.FOOD_ACCOMPANIMENT);
		fries.setName("Batata Frita");
		fries.setUnit("grama");
		fries.setPrice(BigDecimal.valueOf(200));
		fries.setQuantity(BigDecimal.TEN);
		itemDTOS.add(fries);
		ItemDTO cookie = new ItemDTO();
		cookie.setCategory(ItemCategory.FOOD_ACCOMPANIMENT);
		cookie.setName("Cookie");
		cookie.setUnit("unit");
		cookie.setPrice(BigDecimal.ONE);
		itemDTOS.add(cookie);
		return itemDTOS;
	}

	private List<ItemDTO> createDrinkItems() {
		List<ItemDTO> itemDTOS = new ArrayList<>();

		ItemDTO guarana = new ItemDTO();
		guarana.setQuantity(BigDecimal.valueOf(200));
		guarana.setName("Guaraná");
		guarana.setUnit("mililitro");
		guarana.setPrice(BigDecimal.valueOf(9.5));
		guarana.setCategory(ItemCategory.DRINK);
		itemDTOS.add(guarana);

		ItemDTO cocaCola = new ItemDTO();
		cocaCola.setQuantity(BigDecimal.valueOf(100));
		cocaCola.setName("Coca-cola");
		cocaCola.setUnit("militro");
		cocaCola.setPrice(BigDecimal.valueOf(9.8));
		cocaCola.setCategory(ItemCategory.DRINK);
		itemDTOS.add(cocaCola);

		return itemDTOS;
	}

	private List<ItemDTO> createDessertItems() {
		List<ItemDTO> itemDTOS = new ArrayList<>();

		ItemDTO sorvete = new ItemDTO();
		sorvete.setQuantity(BigDecimal.valueOf(200));
		sorvete.setName("Sorvete");
		sorvete.setUnit("mililitro");
		sorvete.setPrice(BigDecimal.valueOf(29.5));
		sorvete.setIngredients(createIceCreamIngredients());
		sorvete.setCategory(ItemCategory.DESSERT);
		itemDTOS.add(sorvete);

		ItemDTO cocada = new ItemDTO();
		cocada.setQuantity(BigDecimal.valueOf(500));
		cocada.setName("Cocada");
		cocada.setUnit("gramas");
		cocada.setPrice(BigDecimal.valueOf(5.5));
		cocada.setCategory(ItemCategory.DESSERT);
		itemDTOS.add(cocada);

		return itemDTOS;
	}

	private List<ItemDTO> createIceCreamIngredients() {
		List<ItemDTO> itemDTOS = new ArrayList<>();

		ItemDTO leite = new ItemDTO();
		leite.setQuantity(BigDecimal.valueOf(50));
		leite.setName("Leite");
		leite.setUnit("mililitro");
		leite.setPrice(BigDecimal.ONE);
		leite.setCategory(ItemCategory.INGREDIENT);
		itemDTOS.add(leite);

		ItemDTO acucar = new ItemDTO();
		acucar.setQuantity(BigDecimal.valueOf(50));
		acucar.setName("Açúcar");
		acucar.setUnit("grama");
		acucar.setPrice(BigDecimal.TEN);
		acucar.setCategory(ItemCategory.INGREDIENT);
		itemDTOS.add(acucar);

		ItemDTO cremeDeLeite = new ItemDTO();
		cremeDeLeite.setQuantity(BigDecimal.valueOf(50));
		cremeDeLeite.setName("Creme de Leite");
		cremeDeLeite.setUnit("mililitro");
		cremeDeLeite.setPrice(BigDecimal.TEN);
		cremeDeLeite.setCategory(ItemCategory.INGREDIENT);
		itemDTOS.add(cremeDeLeite);

		return itemDTOS;
	}

	private List<ItemDTO> createHamburgerIngredients() {
		List<ItemDTO> itemDTOS = new ArrayList<>();

		ItemDTO hamburguer = new ItemDTO();
		hamburguer.setQuantity(BigDecimal.valueOf(100.5));
		hamburguer.setName("Hamburguer");
		hamburguer.setUnit("grama");
		hamburguer.setPrice(BigDecimal.TEN);
		hamburguer.setCategory(ItemCategory.INGREDIENT);
		itemDTOS.add(hamburguer);

		ItemDTO alface = new ItemDTO();
		alface.setQuantity(BigDecimal.valueOf(50.5));
		alface.setName("Alface");
		alface.setUnit("grama");
		alface.setPrice(BigDecimal.TEN);
		alface.setCategory(ItemCategory.INGREDIENT);
		itemDTOS.add(alface);

		ItemDTO queijo = new ItemDTO();
		queijo.setQuantity(BigDecimal.valueOf(30.5));
		queijo.setName("Queijo");
		queijo.setUnit("grama");
		queijo.setPrice(BigDecimal.TEN);
		queijo.setCategory(ItemCategory.ADDITIONAL_INGREDIENT);
		itemDTOS.add(queijo);

		return itemDTOS;
	}

	@NotNull
	private ResponseEntity<OrderResponseDTO> createOrUpdateOrder(OrderRequestDTO orderRequestDTO) {
		ResponseEntity<OrderResponseDTO> orderCreated = orderController.createOrUpdate(orderRequestDTO);
		Assertions.assertNotNull(orderCreated);
		Assertions.assertEquals(orderCreated.getStatusCode().value(), 200);
		return orderCreated;
	}

	private void validateDatabaseIngredients(OrderResponseDTO body) {
		ResponseEntity<OrderRequestDTO> orderSaved = orderController.retrieveOrderById(body.getId());
		Assertions.assertEquals(body.getId(), orderSaved.getBody().getId());
		Assertions.assertEquals(body.getItems().size(), orderSaved.getBody().getItems().size());
		body.getItems().forEach(itemDTO -> {
			Assertions.assertNotNull(itemDTO.getCategory());
		});
		Assertions.assertEquals(body.getStatus(), OrderStatus.PENDING);
		Assertions.assertNull(body.getPayment());
		Assertions.assertNotNull(body.getCreatedDate());
	}

}
