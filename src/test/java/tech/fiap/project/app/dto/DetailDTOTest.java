package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetailDTOTest {

    @Test
    void testConstructorAndGetters() {
        int cap = 100;
        String type = "percentage";
        int value = 50;

        DetailDTO detailDTO = new DetailDTO(cap, type, value);

        assertEquals(cap, detailDTO.getCap(), "The cap value should be 100");
        assertEquals(type, detailDTO.getType(), "The type should be 'percentage'");
        assertEquals(value, detailDTO.getValue(), "The value should be 50");
    }

    @Test
    void testSetters() {
        DetailDTO detailDTO = new DetailDTO(0, null, 0);

        detailDTO.setCap(200);
        detailDTO.setType("fixed");
        detailDTO.setValue(75);

        assertEquals(200, detailDTO.getCap(), "The cap value should be updated to 200");
        assertEquals("fixed", detailDTO.getType(), "The type should be updated to 'fixed'");
        assertEquals(75, detailDTO.getValue(), "The value should be updated to 75");
    }


}
