package tech.fiap.project.domain.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.CreateItem;
import tech.fiap.project.infra.entity.ItemEntity;
import tech.fiap.project.infra.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateItemImpl implements CreateItem {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDTO> createItems(List<ItemDTO> itemDTOs) {
        // Converter de ItemDTO para Item
        List<Item> items = itemDTOs.stream()
                .map(ItemMapper::toDomain)  // Conversão de ItemDTO para Item
                .collect(Collectors.toList());

        // Converter de Item para ItemEntity
        List<ItemEntity> itemEntities = items.stream()
                .map(ItemMapper::toEntity)  // Conversão de Item para ItemEntity
                .collect(Collectors.toList());

        // Salvar no repositório
        List<ItemEntity> savedItemEntities = itemRepository.saveAll(itemEntities);

        // Converter de ItemEntity para Item
        List<Item> savedItems = savedItemEntities.stream()
                .map(ItemMapper::toDomain)  // Conversão de ItemEntity para Item
                .collect(Collectors.toList());

        // Converter de Item para ItemDTO
        return savedItems.stream()
                .map(ItemMapper::toDTO)  // Conversão de Item para ItemDTO
                .collect(Collectors.toList());
    }
}
