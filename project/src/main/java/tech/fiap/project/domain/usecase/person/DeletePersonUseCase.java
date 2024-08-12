package tech.fiap.project.domain.usecase.person;

public interface DeletePersonUseCase {

	void delete(String email);

	void delete(Long id);

}
