package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientCategoryTest {

    @Test
    void testEnumValues() {
        IngredientCategory[] values = IngredientCategory.values();

        assertEquals(2, values.length);
        assertEquals(IngredientCategory.ADDITIONAL, values[0]);
        assertEquals(IngredientCategory.NOT_PURCHASED, values[1]);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(IngredientCategory.ADDITIONAL, IngredientCategory.valueOf("ADDITIONAL"));
        assertEquals(IngredientCategory.NOT_PURCHASED, IngredientCategory.valueOf("NOT_PURCHASED"));
    }
}