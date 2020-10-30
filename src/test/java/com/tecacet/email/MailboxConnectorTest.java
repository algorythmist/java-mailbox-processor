package com.tecacet.email;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MailboxConnectorTest {

    @Test
    public void testGmail() throws Exception {
        Map<String,String> env = System.getenv();
        String username = env.get("GMAIL_USERNAME");
        String password = env.get("GMAIL_PASSWORD");

        MailboxConnector mailboxConnector =
                new GmailMailboxConnector(username, password);
        List<EmailMessage> messages = mailboxConnector.getParsedMessages("INBOX").collect(Collectors.toList());
        assertFalse(messages.isEmpty());
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
