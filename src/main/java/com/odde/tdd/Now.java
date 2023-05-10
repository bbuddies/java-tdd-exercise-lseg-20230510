package com.odde.tdd;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {
    DateService dateService = new DateService();

    public String getString() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .format(dateService.now());
    }

    public void setDateService(DateService dateService) {
        this.dateService = dateService;
    }
}
