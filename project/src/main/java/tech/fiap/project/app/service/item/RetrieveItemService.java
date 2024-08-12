package tech.fiap.project.app.service.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.usecase.item.RetrieveItemUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrieveItemService {

	private RetrieveItemUseCase retrieveItemUseCase;

	public List<ItemDTO> findAll() {
		return retrieveItemUseCase.findAll().stream().map(ItemMapper::toDTO).toList();
	}

	public Optional<ItemDTO> findById(Long id) {
		return retrieveItemUseCase.findById(id).map(ItemMapper::toDTO);
	}

	public List<ItemDTO> findByCategory(String category) {
		ItemCategory itemCategory = ItemCategory.valueOf(category.toUpperCase());
		return retrieveItemUseCase.findByCategory(itemCategory).stream().map(ItemMapper::toDTO).toList();
	}

}
