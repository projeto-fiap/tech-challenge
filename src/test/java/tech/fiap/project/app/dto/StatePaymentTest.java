package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatePaymentTest {

    @Test
    void testEnumValues() {
        StatePayment[] values = StatePayment.values();

        assertEquals(2, values.length);
        assertEquals(StatePayment.ACCEPTED, values[0]);
        assertEquals(StatePayment.REJECTED, values[1]);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(StatePayment.ACCEPTED, StatePayment.valueOf("ACCEPTED"));
        assertEquals(StatePayment.REJECTED, StatePayment.valueOf("REJECTED"));
    }
}