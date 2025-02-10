package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import tech.fiap.project.domain.entity.KitchenStatus;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class KitchenDTOTest {

    @Test
    void testGetterAndSetter() {
        KitchenDTO kitchen = new KitchenDTO();

        LocalDateTime now = LocalDateTime.now();

        kitchen.setOrderId(1L);
        kitchen.setCreationDate(now);
        kitchen.setUpdatedDate(now);
        kitchen.setStatus(KitchenStatus.AWAITING_PRODUCTION);

        assertThat(kitchen.getOrderId()).isEqualTo(1L);
        assertThat(kitchen.getCreationDate()).isEqualTo(now);
        assertThat(kitchen.getUpdatedDate()).isEqualTo(now);
        assertThat(kitchen.getStatus()).isEqualTo(KitchenStatus.AWAITING_PRODUCTION);
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();

        KitchenDTO kitchen1 = new KitchenDTO();
        kitchen1.setOrderId(1L);
        kitchen1.setCreationDate(now);
        kitchen1.setUpdatedDate(now);
        kitchen1.setStatus(KitchenStatus.IN_PRODUCTION);

        KitchenDTO kitchen2 = new KitchenDTO();
        kitchen2.setOrderId(1L);
        kitchen2.setCreationDate(now);
        kitchen2.setUpdatedDate(now);
        kitchen2.setStatus(KitchenStatus.IN_PRODUCTION);

        assertThat(kitchen1).isEqualTo(kitchen2);
        assertThat(kitchen1.hashCode()).isEqualTo(kitchen2.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime now = LocalDateTime.now();
        KitchenDTO kitchen = new KitchenDTO();
        kitchen.setOrderId(1L);
        kitchen.setCreationDate(now);
        kitchen.setUpdatedDate(now);
        kitchen.setStatus(KitchenStatus.DONE);

        String expectedToString = "KitchenDTO(orderId=1, creationDate=" + now + ", updatedDate=" + now + ", status=DONE)";
        assertThat(kitchen.toString()).isEqualTo(expectedToString);
    }
}
