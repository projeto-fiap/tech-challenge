// package tech.fiap.project.app.controller;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import tech.fiap.project.app.dto.PersonDTO;
// import tech.fiap.project.app.service.person.DeletePersonService;
// import tech.fiap.project.app.service.person.RetrievePersonService;
// import tech.fiap.project.app.service.person.SavePersonService;
// import tech.fiap.project.app.service.person.UpdatePersonService;
// import tech.fiap.project.domain.entity.Person;
// import tech.fiap.project.domain.entity.Role;
// import tech.fiap.project.infra.exception.PersonNotFoundException;
// import tech.fiap.project.infra.exception.UnauthorizedException;
//
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
//
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// @ExtendWith(MockitoExtension.class)
// class PersonControllerTest {
//
// @Mock
// private RetrievePersonService retrievePersonService;
//
// @Mock
// private UpdatePersonService updatePersonService;
//
// @Mock
// private SavePersonService savePersonService;
//
// @Mock
// private DeletePersonService deletePersonService;
//
// @InjectMocks
// private PersonController personController;
//
// private PersonDTO personDTO;
//
// private Person person;
//
// @BeforeEach
// void setUp() {
// personDTO = new PersonDTO();
// personDTO.setId(1L);
// personDTO.setName("John Doe");
// personDTO.setRole(Role.USER);
//
// person = new Person();
// person.setId(1L);
// person.setName("John Doe");
// person.setRole(Role.USER);
// }
//
// @Test
// void testGetPersonByCpf_Found() {
// when(retrievePersonService.findByCpf("12345678901")).thenReturn(Optional.of(personDTO));
//
// ResponseEntity<PersonDTO> response = personController.getPersonByCpf("12345678901");
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(personDTO, response.getBody());
// }
//
// @Test
// void testGetPersonByCpf_NotFound() {
// when(retrievePersonService.findByCpf("12345678901")).thenReturn(Optional.empty());
//
// ResponseEntity<PersonDTO> response = personController.getPersonByCpf("12345678901");
//
// assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
// }
//
// @Test
// void testGetPerson_Found() {
// when(retrievePersonService.findById(1L)).thenReturn(Optional.of(personDTO));
//
// ResponseEntity<PersonDTO> response = personController.getPerson(1L);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(personDTO, response.getBody());
// }
//
// @Test
// void testGetPerson_NotFound() {
// when(retrievePersonService.findById(1L)).thenReturn(Optional.empty());
//
// ResponseEntity<PersonDTO> response = personController.getPerson(1L);
//
// assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
// }
//
// @Test
// void testGetPersons() {
// when(retrievePersonService.findAll()).thenReturn(Collections.singletonList(personDTO));
//
// ResponseEntity<List<PersonDTO>> response = personController.getPersons();
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(1, response.getBody().size());
// assertEquals(personDTO, response.getBody().get(0));
// }
//
// @Test
// void testSavePerson_UserRole() {
// personDTO.setRole(Role.USER);
// when(savePersonService.save(any(), any())).thenReturn(person);
//
// ResponseEntity<PersonDTO> response = personController.savePerson(personDTO);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(personDTO.getId(), response.getBody().getId());
// }
//
// @Test
// void testSavePerson_UnauthorizedRole() {
// personDTO.setRole(Role.ADMIN);
//
// assertThrows(UnauthorizedException.class, () ->
// personController.savePerson(personDTO));
// }
//
// @Test
// void testSavePersonAdmin() {
// when(savePersonService.save(any(), any())).thenReturn(person);
//
// ResponseEntity<PersonDTO> response = personController.savePersonAdmin(personDTO);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(personDTO.getId(), response.getBody().getId());
// }
//
// @Test
// void testUpdatePerson_Found() {
// when(updatePersonService.update(personDTO, 1L)).thenReturn(personDTO);
//
// ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, 1L);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// assertEquals(personDTO, response.getBody());
// }
//
// @Test
// void testUpdatePerson_NotFound() {
// when(updatePersonService.update(personDTO, 1L)).thenThrow(new
// PersonNotFoundException("Person not found"));
//
// ResponseEntity<PersonDTO> response = personController.updatePerson(personDTO, 1L);
//
// assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
// }
//
// @Test
// void testDeletePerson_Found() {
// doNothing().when(deletePersonService).delete(personDTO);
//
// ResponseEntity<Void> response = personController.deletePerson(personDTO);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// }
//
// @Test
// void testDeletePerson_NotFound() {
// doThrow(new PersonNotFoundException("Person not
// found")).when(deletePersonService).delete(personDTO);
//
// ResponseEntity<Void> response = personController.deletePerson(personDTO);
//
// assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
// }
//
// @Test
// void testDeletePersonById_Found() {
// doNothing().when(deletePersonService).delete(1L);
//
// ResponseEntity<Void> response = personController.deletePersonById(1L);
//
// assertEquals(HttpStatus.OK, response.getStatusCode());
// }
//
// @Test
// void testDeletePersonById_NotFound() {
// doThrow(new PersonNotFoundException("Person not
// found")).when(deletePersonService).delete(1L);
//
// ResponseEntity<Void> response = personController.deletePersonById(1L);
//
// assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
// }
//
// }