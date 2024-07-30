package tech.fiap.project;

import jakarta.transaction.Transactional;
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
import tech.fiap.project.app.dto.PersonDTO;
import tech.fiap.project.domain.entity.OrderStatus;
import tech.fiap.project.infra.entity.ItemCategory;
import tech.fiap.project.infra.exception.PersonNotFoundException;

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
	@Transactional
	void createOrderAnonymous() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		ResponseEntity<OrderDTO> orderCreated = getOrderDTOResponseEntity(orderDTO);
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		Assertions.assertNull(body.getPerson());
	}

	private List<ItemDTO> createItems() {
		List<ItemDTO> items = new ArrayList<>();

		ItemDTO bigMac = new ItemDTO();
		bigMac.setQuantity(BigDecimal.ONE);
		bigMac.setName("Big Mac");
		bigMac.setUnit("unit");
		bigMac.setPrice(BigDecimal.valueOf(20));
		bigMac.setIngredients(createHamburgerIngredients());
		bigMac.setCategory(ItemCategory.FOOD);
		items.add(bigMac);

		ItemDTO guarana = new ItemDTO();
		guarana.setQuantity(BigDecimal.valueOf(200));
		guarana.setName("Guaraná");
		guarana.setUnit("mililitro");
		guarana.setPrice(BigDecimal.valueOf(5));
		guarana.setCategory(ItemCategory.DRINK);
		items.add(guarana);

		ItemDTO batataFrita = new ItemDTO();
		batataFrita.setQuantity(BigDecimal.valueOf(10));
		batataFrita.setName("Batata Frita");
		batataFrita.setPrice(BigDecimal.valueOf(10));
		batataFrita.setUnit("grama");
		batataFrita.setCategory(ItemCategory.FOOD_ACCOMPANIMENT);
		items.add(batataFrita);

		ItemDTO sorvete = new ItemDTO();
		sorvete.setQuantity(BigDecimal.valueOf(100));
		sorvete.setName("Sorvete");
		sorvete.setPrice(BigDecimal.valueOf(10));
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
		leite.setPrice(BigDecimal.ONE);
		leite.setUnit("mililitro");
		leite.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(leite);

		ItemDTO acucar = new ItemDTO();
		acucar.setQuantity(BigDecimal.valueOf(50));
		acucar.setName("Açúcar");
		acucar.setPrice(BigDecimal.ONE);
		acucar.setUnit("grama");
		acucar.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(acucar);

		ItemDTO cremeDeLeite = new ItemDTO();
		cremeDeLeite.setQuantity(BigDecimal.valueOf(50));
		cremeDeLeite.setName("Creme de Leite");
		cremeDeLeite.setPrice(BigDecimal.TEN);
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
		hamburguer.setPrice(BigDecimal.TEN);
		hamburguer.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(hamburguer);

		ItemDTO alface = new ItemDTO();
		alface.setQuantity(BigDecimal.valueOf(50.5));
		alface.setName("Alface");
		alface.setPrice(BigDecimal.TEN);
		alface.setUnit("grama");
		alface.setCategory(ItemCategory.INGREDIENT);
		ingredients.add(alface);

		ItemDTO queijo = new ItemDTO();
		queijo.setQuantity(BigDecimal.valueOf(30.5));
		queijo.setName("Queijo");
		queijo.setPrice(BigDecimal.ONE);
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
		Assertions.assertNotNull(body.getAwaitingTime());
	}

	@Test
	@Transactional
	void createOrderWithPerson() {
		DocumentDTO document = new DocumentDTO();
		document.setType(CPF);
		document.setValue("1234567890");

		PersonDTO personDTO = new PersonDTO();
		personDTO.setDocument(List.of(document));

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		orderDTO.setPerson(personDTO);

		ResponseEntity<OrderDTO> orderCreated = getOrderDTOResponseEntity(orderDTO);
		OrderDTO body = orderCreated.getBody();
		Assertions.assertNotNull(body);
		validateDatabaseIngredients(body);
		validatePerson(body, document);
	}

	private static void validatePerson(OrderDTO body, DocumentDTO document) {
		PersonDTO person = body.getPerson();
		Assertions.assertNotNull(person.getId());
		Assertions.assertNotNull(person.getEmail());
		List<DocumentDTO> documents = person.getDocument();
		Assertions.assertEquals(documents.size(), 1);
		Assertions.assertEquals(documents.get(0).getType(), document.getType());
		Assertions.assertEquals(documents.get(0).getValue(), document.getValue());
	}

	@Test
	@Transactional
	void createOrderWithPersonNotFound() {
		DocumentDTO document = new DocumentDTO();
		document.setType(CPF);
		document.setValue("123456789012");

		PersonDTO personDTO = new PersonDTO();
		personDTO.setDocument(List.of(document));

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setItems(createItems());
		orderDTO.setPerson(personDTO);

		Assertions.assertThrows(PersonNotFoundException.class, () -> orderController.createOrUpdate(orderDTO));
	}
}