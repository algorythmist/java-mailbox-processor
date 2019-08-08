package com.tecacet.email;

import org.junit.Test;

import javax.mail.MessagingException;

public class MailboxConnectorTest {

    @Test
    public void testGmail() {
        String username = System.getenv("gmail.username");
        String password = System.getenv("gmail.password");

        try (MailboxConnector mailboxConnector =
                     new GmailMailboxConnector(username, password)) {
            mailboxConnector.getMessages("INBOX").forEach(message -> {
                try {
                    System.out.println(message.getSubject());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testYahoo() {
        String username = System.getenv("yahoo.username");
        String password = System.getenv("yahoo.password");

        try (MailboxConnector mailboxConnector =
                     new YahooMailboxConnector(username, password)) {
            mailboxConnector.getMessages("INBOX").forEach(message -> {
                try {
                    System.out.println(message.getSubject());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
