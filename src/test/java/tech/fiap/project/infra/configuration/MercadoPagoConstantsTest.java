package tech.fiap.project.infra.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MercadoPagoConstantsTest {

    @Test
    void testBaseUri() {
        assertEquals("https://api.mercadopago.com/", MercadoPagoConstants.BASE_URI);
    }

    @Test
    void testBasePaymentMethod() {
        String expected = "/instore/orders/qr/seller/collectors/%s/pos/%s/qrs";
        assertEquals(expected, MercadoPagoConstants.BASE_PAYMENT_METHOD);
    }
}