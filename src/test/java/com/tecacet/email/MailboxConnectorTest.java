package com.tecacet.email;

import org.junit.Test;

public class MailboxConnectorTest {

    @Test
    public void testGmail() throws Exception {
        String username = System.getenv("gmail.username");
        String password = System.getenv("gmail.password");

        MailboxConnector mailboxConnector =
                new GmailMailboxConnector(username, password);
        mailboxConnector.getParsedMessages("INBOX").forEach(message -> System.out.println(message.getSubject()));
        mailboxConnector.close();
    }

    //TODO: @Test
    public void testYahoo() {

        String username = System.getenv("yahoo.username");
        String password = System.getenv("yahoo.password");

        try (MailboxConnector mailboxConnector =
                     new YahooMailboxConnector(username, password)) {
            mailboxConnector.getParsedMessages("INBOX").forEach(message -> System.out.println(message.getSubject()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
