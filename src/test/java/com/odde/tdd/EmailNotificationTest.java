package com.odde.tdd;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EmailNotificationTest {
    @Test
    void welcome_mail() {
//        MockMailService mockMailService = new MockMailService();
        IMailService mockMailService = mock(IMailService.class);
        EmailNotification emailNotification = new EmailNotification(mockMailService);
        emailNotification.welcome("aaa@gmail.com");
        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(mockMailService).send(captor.capture());
        assertEquals("Welcome", captor.getValue().getTitle());
        assertEquals("Hello", captor.getValue().getContent());
    }
}

class MockMailService implements IMailService {

    private int invoked = 0;
    private Email mail;

    @Override
    public void send(Email mail) {
        this.invoked++;
        this.mail = mail;
    }

    public void verify() {
        assertEquals(1, this.invoked);
        assertEquals("Welcome", this.mail.getTitle());
        assertEquals("Hello", this.mail.getContent());
        //

    }
}