package tech.fiap.project.domain.usecase.impl;

import org.springframework.stereotype.Component;
import tech.fiap.project.app.adapter.ItemRepositoryMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.usecase.GetAllItems;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllItemsImpl implements GetAllItems {

    private final ItemRepository itemRepository;

    public GetAllItemsImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        return itemEntities.stream()
                .map(ItemRepositoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
