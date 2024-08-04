package tech.fiap.project.domain.usecase.kitchen;

import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface KitchenCreateUseCase {

    Kitchen execute(Kitchen kitchen);
}
