package com.tecacet.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import java.util.Properties;

/**
 * MailboxConnector configured for gmail
 */
public class GmailMailboxConnector extends AbstractMailboxConnector {

    private static final String DEFAULT_PROTOCOL = "pop3";
    private static final String GMAIL_POP3_HOST = "pop.gmail.com";
    private static final int GMAIL_POP3_PORT = 995;

    public GmailMailboxConnector(String username, String password) {
        super(username, password, null);
    }

    @Override
    protected Store connect(String username, String password, Properties properties) throws MessagingException {
        properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.pop3.socketFactory.fallback", false);
        URLName url = new URLName(DEFAULT_PROTOCOL, GMAIL_POP3_HOST, GMAIL_POP3_PORT, "", username, password);
        Session session = Session.getInstance(properties, null);
        Store store = session.getStore(url);
        store.connect();
        return store;
    }
}
