package tech.fiap.project.infra.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnauthorizedExceptionTest {
    //@Test
    void testUnauthorizedException() {
        UnauthorizedException result = new UnauthorizedException(org.springframework.http.HttpStatus.UNAUTHORIZED);
        assertEquals("Você não tem permissão para criar um admin", result.getMessage());
        assertEquals(org.springframework.http.HttpStatus.UNAUTHORIZED, result.getHttpStatusCode());
    }

}