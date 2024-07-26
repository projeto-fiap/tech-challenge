package tech.fiap.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.controller.OrderController;
import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static tech.fiap.project.domain.entity.DocumentType.CPF;

@ActiveProfiles("test")
@SpringBootTest
class CreateOrdersStepsTests {

	@Autowired
	OrderController orderController;

	@Test
	void createOrderAllSteps() {
		ResponseEntity<OrderDTO> foodStep = createOrder();
		ResponseEntity<OrderDTO> foodAccompanimentCreated = updateOrder(foodStep, createFoodAccompanimentItens());
		ResponseEntity<OrderDTO> drinkOrderCreated = updateOrder(foodAccompanimentCreated, createDrinkItems());
		ResponseEntity<OrderDTO> dessertOrderCreated = updateOrder(drinkOrderCreated, createDessertItems());
		Assertions.assertEquals(Objects.requireNonNull(dessertOrderCreated.getBody()).getId(), Objects.requireNonNull(foodStep.getBody()).getId());
	}

	private ResponseEntity<OrderDTO> updateOrder(ResponseEntity<OrderDTO> orderCreated, List<ItemDTO> newItens) {
		OrderDTO body = orderCreated.getBody();
		List<ItemDTO> orderItems = Objects.requireNonNull(body).getItems();
		ArrayList newOrderItens = new ArrayList<OrderDTO>();
		newOrderItens.addAll(orderItems);
		newOrderItens.addAll(newItens);
		body.setItems(newOrderItens);
		ResponseEntity<OrderDTO> orderUpdated = createOrUpdateOrder(body);
		validateReturn(orderUpdated);
		return orderUpdated;
	}

	private ResponseEntity<OrderDTO> createOrder(){
		OrderDTO orderDTO = new OrderDTO(null, null,null,null, createFoodItems(),null,null);
		ResponseEntity<OrderDTO> foodOrderCreated = createOrUpdateOrder(orderDTO);
		validateReturn(foodOrderCreated);
		return foodOrderCreated;
	}
	private void validateReturn(ResponseEntity<OrderDTO> orderCreated) {
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		Assertions.assertNull(body.getUser());
	}

	private List<ItemDTO> createFoodItems(){
		List<ItemDTO> itemDTOS = new ArrayList<>();
		itemDTOS.add(
				new ItemDTO(BigDecimal.ONE, "Big Mac", "unit", createHamburgerIngredients(), ItemCategory.FOOD));
		return itemDTOS;
	}

	private List<ItemDTO> createFoodAccompanimentItens(){
		List<ItemDTO> itemDTOS = new ArrayList<>();
		itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(200), "Batafrita", "grama", null,ItemCategory.FOOD_ACCOMPANIMENT));

		itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(1), "Cookie", "unit", null, ItemCategory.FOOD_ACCOMPANIMENT));
	return itemDTOS;
	}
	private List<ItemDTO> createDrinkItems(){
		List<ItemDTO> itemDTOS = new ArrayList<>();

		itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(200), "Guaraná", "mililitro", null,ItemCategory.DRINK));
		itemDTOS.add(new ItemDTO(BigDecimal.valueOf(100), "Coca-cola", "militro", null, ItemCategory.DRINK));
	return itemDTOS;
	}

	private List<ItemDTO> createDessertItems(){
		List<ItemDTO> itemDTOS = new ArrayList<>();

		itemDTOS.add(new ItemDTO(BigDecimal.valueOf(200), "Sorvete", "mililitro", createIceCreamIngredients(),ItemCategory.DESSERT));
		itemDTOS.add(new ItemDTO(BigDecimal.valueOf(500), "Cocada", "gramas", null, ItemCategory.DESSERT));
	return itemDTOS;
	}

	private List<ItemDTO> createIceCreamIngredients(){
		List<ItemDTO> itemDTOS = new ArrayList<>();

		itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(50), "Leite", "mililitro", null, ItemCategory.INGREDIENT));
		itemDTOS.add(new ItemDTO(BigDecimal.valueOf(50), "Açúcar", "grama", null, ItemCategory.INGREDIENT));
		itemDTOS.add(new ItemDTO(BigDecimal.valueOf(50), "Creme de Leite", "mililitro", null, ItemCategory.INGREDIENT));
	return itemDTOS;
	}

	private List<ItemDTO> createHamburgerIngredients(){
		List<ItemDTO> itemDTOS = new ArrayList<>();

		itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(100.5), "Hamburguer", "grama", null, ItemCategory.INGREDIENT));
				itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(50.5), "Alface", "grama", null, ItemCategory.INGREDIENT));
						itemDTOS.add(
				new ItemDTO(BigDecimal.valueOf(30.5), "Queijo", "grama", null, ItemCategory.ADDITIONAL_INGREDIENT));
	return itemDTOS;
	}

	@NotNull
	private ResponseEntity<OrderDTO> createOrUpdateOrder(OrderDTO orderDTO) {
		ResponseEntity<OrderDTO> orderCreated = orderController.createOrUpdate(orderDTO);
		Assertions.assertNotNull(orderCreated);
		Assertions.assertEquals(orderCreated.getStatusCode().value(), 200);
		return orderCreated;
	}

	private void validateDatabaseIngredients(OrderDTO body) {
		ResponseEntity<OrderDTO> orderSaved = orderController.retrieveOrderById(body.getId());
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
