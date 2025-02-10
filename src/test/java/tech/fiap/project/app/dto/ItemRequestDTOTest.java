package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class ItemRequestDTOTest {

    @Test
    void testGetterAndSetter() {
        ItemRequestDTO item = new ItemRequestDTO();

        item.setId(1L);
        item.setQuantity(new BigDecimal("10.5"));
        item.setUnit("kg");

        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getQuantity()).isEqualTo(new BigDecimal("10.5"));
        assertThat(item.getUnit()).isEqualTo("kg");
    }

    @Test
    void testEqualsAndHashCode() {
        ItemRequestDTO item1 = new ItemRequestDTO();
        item1.setId(1L);
        item1.setQuantity(new BigDecimal("10.5"));
        item1.setUnit("kg");

        ItemRequestDTO item2 = new ItemRequestDTO();
        item2.setId(1L);
        item2.setQuantity(new BigDecimal("10.5"));
        item2.setUnit("kg");

        assertThat(item1).isEqualTo(item2);
        assertThat(item1.hashCode()).isEqualTo(item2.hashCode());
    }

    @Test
    void testToString() {
        ItemRequestDTO item = new ItemRequestDTO();
        item.setId(1L);
        item.setQuantity(new BigDecimal("10.5"));
        item.setUnit("kg");

        String expectedToString = "ItemRequestDTO(id=1, quantity=10.5, unit=kg)";
        assertThat(item.toString()).isEqualTo(expectedToString);
    }
}
