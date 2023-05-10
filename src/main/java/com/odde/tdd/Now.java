package com.odde.tdd;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {
    private final TimeProvider timeProvider;

    public Now() {
        this.timeProvider = new SystemTime();
    }

    public Now(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String getString() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .format(timeProvider.getNow());
    }
}

