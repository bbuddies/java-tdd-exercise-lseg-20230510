package com.odde.tdd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StubTimeProvider implements TimeProvider {
    @Override
    public Date getNow() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse("2023-05-10 14:27:0.123");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
