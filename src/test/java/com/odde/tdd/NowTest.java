package com.odde.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NowTest {

    @Test
    public void testNow(){

        //Given
        Now now = new Now();
        DateService dateService = mock(DateService.class);
        now.setDateService(dateService);
        Date date = new Date();
        when(dateService.now()).thenReturn(date);
        String expected = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .format(date);

        //When
        String actual = now.getString();

        //Then
        Assertions.assertEquals(expected, actual);
    }

}