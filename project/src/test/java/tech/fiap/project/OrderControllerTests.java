package tech.fiap.project;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.controller.ItemController;
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

import static tech.fiap.project.domain.entity.DocumentType.CPF;

@ActiveProfiles("test")
@SpringBootTest
class OrderControllerTests {

	@Autowired
	OrderController orderController;

	@Autowired
	ItemController itemController;

	@Test
	void retrieveOrders() {
		ResponseEntity<List<OrderDTO>> listResponseEntity = orderController.retrieveOrders();
		Assertions.assertNotNull(listResponseEntity);
		Assertions.assertEquals(listResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
		Assertions.assertNotNull(listResponseEntity.getBody());
		Assertions.assertTrue(listResponseEntity.getBody().isEmpty());
	}

	@Test
	void createOrderAnonymous() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		ResponseEntity<OrderDTO> orderCreated = getOrderDTOResponseEntity(orderDTO);
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		Assertions.assertNull(body.getUser());
	}

	private List<ItemDTO> createItems() {
		List<ItemDTO> items = new ArrayList<>();

		ItemDTO bigMac = new ItemDTO();
		bigMac.setQuantity(BigDecimal.ONE);
		bigMac.setName("Big Mac");
		bigMac.setUnit("unit");
		bigMac.setIngredients(createHamburgerIngredients());
		bigMac.setCategory(ItemCategory.FOOD);
		items.add(bigMac);

		ItemDTO guarana = new ItemDTO();
		guarana.setQuantity(BigDecimal.valueOf(200));
		guarana.setName("Guaraná");
		guarana.setUnit("mililitro");
		guarana.setCategory(ItemCategory.DRINK);
		items.add(guarana);

		ItemDTO batataFrita = new ItemDTO();
		batataFrita.setQuantity(BigDecimal.valueOf(10));
		batataFrita.setName("Batata Frita");
		batataFrita.setUnit("grama");
		batataFrita.setCategory(ItemCategory.FOOD_ACCOMPANIMENT);
		items.add(batataFrita);

		ItemDTO sorvete = new ItemDTO();
		sorvete.setQuantity(BigDecimal.valueOf(100));
		sorvete.setName("Sorvete");
		sorvete.setUnit("grama");
		sorvete.setIngredients(createIceCreamIngredients());
		sorvete.setCategory(ItemCategory.DESSERT);
		items.add(sorvete);
		return itemController.createItems(items).getBody();
	}

	private List<ItemDTO> createIceCreamIngredients() {
		List<ItemDTO> ingredients = new ArrayList<>();

		ItemDTO leite = new ItemDTO();
		leite.setQuantity(BigDecimal.valueOf(50));
		leite.setName("Leite");
		leite.setUnit("mililitro");
		leite.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(leite);

		ItemDTO acucar = new ItemDTO();
		acucar.setQuantity(BigDecimal.valueOf(50));
		acucar.setName("Açúcar");
		acucar.setUnit("grama");
		acucar.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(acucar);

		ItemDTO cremeDeLeite = new ItemDTO();
		cremeDeLeite.setQuantity(BigDecimal.valueOf(50));
		cremeDeLeite.setName("Creme de Leite");
		cremeDeLeite.setUnit("mililitro");
		cremeDeLeite.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(cremeDeLeite);

		return ingredients;
	}

	private List<ItemDTO> createHamburgerIngredients() {
		List<ItemDTO> ingredients = new ArrayList<>();

		ItemDTO hamburguer = new ItemDTO();
		hamburguer.setQuantity(BigDecimal.valueOf(100.5));
		hamburguer.setName("Hamburguer");
		hamburguer.setUnit("grama");
		hamburguer.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(hamburguer);

		ItemDTO alface = new ItemDTO();
		alface.setQuantity(BigDecimal.valueOf(50.5));
		alface.setName("Alface");
		alface.setUnit("grama");
		alface.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(alface);

		ItemDTO queijo = new ItemDTO();
		queijo.setQuantity(BigDecimal.valueOf(30.5));
		queijo.setName("Queijo");
		queijo.setUnit("grama");
		queijo.setCategory(ItemCategory.ADDITIONAL_INGREDIENT);
		ingredients.add(queijo);

		return ingredients;
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
		DocumentDTO document = new DocumentDTO();
		document.setType(CPF);
		document.setValue("1234567890");

		UserDTO userDTO = new UserDTO();
		userDTO.setDocument(List.of(document));

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		orderDTO.setUser(userDTO);

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
		Assertions.assertEquals(documents.size(), 1);
		Assertions.assertEquals(documents.get(0).getType(), document.getType());
		Assertions.assertEquals(documents.get(0).getValue(), document.getValue());
	}

	@Test
	void createOrderWithUserNotFound() {
		DocumentDTO document = new DocumentDTO();
		document.setType(CPF);
		document.setValue("123456789012");

		UserDTO userDTO = new UserDTO();
		userDTO.setDocument(List.of(document));

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		orderDTO.setUser(userDTO);

		Assertions.assertThrows(UserNotFoundException.class, () -> orderController.createOrUpdate(orderDTO));
	}
}