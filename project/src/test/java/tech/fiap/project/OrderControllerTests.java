package tech.fiap.project;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import tech.fiap.project.app.controller.OrderController;
import tech.fiap.project.app.dto.DocumentDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import static tech.fiap.project.domain.entity.DocumentType.CPF;

@SpringBootTest
class OrderControllerTests {

	@Autowired
	OrderController orderController;
	@Test
	void retrieveOrders() {
		ResponseEntity<List<OrderDTO>> listResponseEntity = orderController.retrieveOrders();
		Assertions.assertNotNull(listResponseEntity);
		Assertions.assertEquals(listResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
		Assertions.assertNotNull(listResponseEntity.getBody());
		Assertions.assertFalse(listResponseEntity.getBody().isEmpty());
	}

	@Test
	void createOrderAnonymous() {
		OrderDTO orderDTO = new OrderDTO(null, null,null,null,createItems(),null,null);
		ResponseEntity<OrderDTO> orderCreated = getOrderDTOResponseEntity(orderDTO);
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		Assertions.assertNull(body.getUser());
	}

	private List<ItemDTO> createItems(){
		return List.of(
				new ItemDTO(BigDecimal.ONE, "Big Mac", "unit", createHamburgerIngredients(), ItemCategory.FOOD),
				new ItemDTO(BigDecimal.valueOf(200), "Guaraná", "mililitro", null,ItemCategory.DRINK),
				new ItemDTO(BigDecimal.valueOf(10), "Batata Frita", "grama", null, ItemCategory.FOOD_ACCOMPANIMENT),
				new ItemDTO(BigDecimal.valueOf(100), "Sorvete", "grama", createIceCreamIngredients(), ItemCategory.DESSERT));
	}

	private List<ItemDTO> createIceCreamIngredients(){
		return List.of(
				new ItemDTO(BigDecimal.valueOf(50), "Leite", "mililitro", null, ItemCategory.INGREDIENT),
				new ItemDTO(BigDecimal.valueOf(50), "Açúcar", "grama", null, ItemCategory.INGREDIENT),
				new ItemDTO(BigDecimal.valueOf(50), "Creme de Leite", "mililitro", null, ItemCategory.INGREDIENT)
		);
	}

	private List<ItemDTO> createHamburgerIngredients(){
		return List.of(
				new ItemDTO(BigDecimal.valueOf(100.5), "Hamburguer", "grama", null, ItemCategory.INGREDIENT),
				new ItemDTO(BigDecimal.valueOf(50.5), "Alface", "grama", null, ItemCategory.INGREDIENT),
				new ItemDTO(BigDecimal.valueOf(30.5), "Queijo", "grama", null, ItemCategory.ADDITIONAL_INGREDIENT)
		);
	}

	@NotNull
	private ResponseEntity<OrderDTO> getOrderDTOResponseEntity(OrderDTO orderDTO) {
		ResponseEntity<OrderDTO> orderCreated = orderController.createOrUpdate(orderDTO);
		Assertions.assertNotNull(orderCreated);
		Assertions.assertEquals(orderCreated.getStatusCode().value(), 200);
		return orderCreated;
	}

	private void validateDatabaseIngredients(OrderDTO body) {
		ResponseEntity<OrderDTO> orderSaved = orderController.retrieveOrderById(body.getId());
		Assertions.assertEquals(body.getId(), orderSaved.getBody().getId());
		Assertions.assertEquals(body.getItems().size(), orderSaved.getBody().getItems().size());
		Assertions.assertEquals(body.getStatus(), OrderStatus.PENDING);
		Assertions.assertNull(body.getPayment());
		Assertions.assertNotNull(body.getCreatedDate());
		Assertions.assertNull(body.getUpdatedDate());
	}

	@Test
	void createOrderWithUser() {
		DocumentDTO document = new DocumentDTO(CPF, "1234567890");
		UserDTO userDTO = new UserDTO(null, null,null,List.of(document));
		OrderDTO orderDTO = new OrderDTO(null, null,null,null,createItems(),null,userDTO);
		ResponseEntity<OrderDTO> orderCreated = getOrderDTOResponseEntity(orderDTO);
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		validateUser(body, document);
	}

	private static void validateUser(OrderDTO body, DocumentDTO document) {
		UserDTO user = body.getUser();
		Assertions.assertNotNull(user.getId());
		Assertions.assertNotNull(user.getEmail());
		List<DocumentDTO> documents = user.getDocument();
		Assertions.assertEquals(documents.size(),1);
		Assertions.assertEquals(documents.get(0).getType(), document.getType());
		Assertions.assertEquals(documents.get(0).getValue(), document.getValue());
	}

	@Test
	void createOrderWithUserNotFound() {
		DocumentDTO document = new DocumentDTO(CPF, "123456789012");
		UserDTO userDTO = new UserDTO(null, null,null,List.of(document));
		OrderDTO orderDTO = new OrderDTO(null, null,null,null,createItems(),null,userDTO);
		Assertions.assertThrows(UserNotFoundException.class,()->orderController.createOrUpdate(orderDTO));
	}

}
