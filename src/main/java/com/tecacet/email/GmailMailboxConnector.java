package com.tecacet.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

/**
 * MailboxConnector configured for gmail using IMAP
 */
public class GmailMailboxConnector extends AbstractMailboxConnector {


    private static final String IMAP_PROTOCOL = "imaps";
    private static final String GMAIL_IMAP_HOST = "imap.gmail.com";

    public GmailMailboxConnector(String username, String password) {
        super(username, password, null);
    }

    @Override
    protected Store connect(String username, String password, Properties properties) throws MessagingException {
        properties.setProperty("mail.store.protocol", IMAP_PROTOCOL);

        Session session = Session.getInstance(properties, null);
        Store store = session.getStore(IMAP_PROTOCOL);
        store.connect(GMAIL_IMAP_HOST, username, password);
        return store;
    }
}
