package tech.fiap.project.app.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.impl.item.UpdateItemUseCaseImpl;
import tech.fiap.project.domain.usecase.item.UpdateItemUseCase;

@Service
@RequiredArgsConstructor
public class UpdateItemService {

    private final UpdateItemUseCase updateItemUseCase;

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        // Converte o DTO para entidade de domínio
        Item item = ItemMapper.toDomain(itemDTO);
        // Executa o caso de uso para atualizar o item
        Item updatedItem = updateItemUseCase.execute(id, item);
        // Converte o item atualizado de volta para DTO
        return ItemMapper.toDTO(updatedItem);
    }
}
