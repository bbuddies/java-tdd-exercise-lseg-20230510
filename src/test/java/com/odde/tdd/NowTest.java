package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NowTest {
    @Test
    void now_string() throws ParseException {
        // Set current time
//        TimeProvider stubTimeProvider = new StubTimeProvider();

        TimeProvider stubTimeProvider = mock(TimeProvider.class);
        when(stubTimeProvider.getNow()).thenReturn(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse("2023-05-10 14:27:0.123"));
        Now now = new Now(stubTimeProvider);
        assertEquals("2023-05-10 02:27:00.123", now.getString());
    }
}