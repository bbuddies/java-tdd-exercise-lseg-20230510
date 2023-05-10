package com.odde.tdd;

public class EmailNotification {

    private final IMailService mailService;

    public EmailNotification() {
        mailService = new MailService();
    }

    public EmailNotification(IMailService mailService){
        this.mailService = mailService;
    }

    public void welcome(String email) {
        Email mail = new Email();
        mail.setFrom("zbcjackson@odd-e.com");
        mail.setTo(email);
        mail.setTitle("Welcome");
        mail.setContent("Hello");
        mailService.send(mail);
    }
}
