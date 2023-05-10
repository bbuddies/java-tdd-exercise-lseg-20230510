package com.odde.tdd;

import java.util.Date;

public class SystemTime implements TimeProvider {

    @Override
    public Date getNow() {
        return new Date();
    }
}
