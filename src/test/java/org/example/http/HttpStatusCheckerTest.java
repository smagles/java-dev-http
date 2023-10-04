package org.example.http;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpStatusCheckerTest {

    @Test
    void testValidStatusCode() throws HttpStatusException {
        //Given
        HttpStatusChecker checker = new HttpStatusChecker();

        //When
        final int code =200;
        final String url = checker.getStatusImage(code);

        //Then
        assertEquals("https://http.cat/200.jpg", url);
    }

    @Test
    void testInvalidStatusCode() {
        //Given
        HttpStatusChecker checker = new HttpStatusChecker();

        //When & Then
        final int code =10000;
        assertThrows(HttpStatusException.class, () -> checker.getStatusImage(code));
    }

    @Test
   void testInvalidUrl() {
        //Given
        HttpStatusChecker checker = new HttpStatusChecker();

        //When & Then
        final int code =-1;
        assertThrows(HttpStatusException.class, () -> checker.getStatusImage(code));
    }
}
