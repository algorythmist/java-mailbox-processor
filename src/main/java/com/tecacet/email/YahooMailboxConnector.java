package com.tecacet.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class YahooMailboxConnector extends AbstractMailboxConnector {

    public YahooMailboxConnector(String username, String password) {
        super(username, password, null);
    }

    @Override
    protected Store connect(String username, String password, Properties properties)
            throws MessagingException {
        properties.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect("imap.mail.yahoo.com", username, password);
        return store;
    }
}
