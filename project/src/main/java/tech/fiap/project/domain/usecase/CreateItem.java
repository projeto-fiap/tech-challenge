package tech.fiap.project.domain.usecase;



import tech.fiap.project.app.dto.ItemDTO;
import java.util.List;

public interface CreateItem {
    List<ItemDTO> createItems(List<ItemDTO> itemDTOs);
}

