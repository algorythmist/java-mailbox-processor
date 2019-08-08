package com.tecacet.email;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

public abstract class AbstractMailboxConnector implements MailboxConnector {

    private boolean deleteAfterProcessing = false;
    private boolean readOnly = true;

    private Store store;
    private Folder folder = null;

    public AbstractMailboxConnector(String username, String password, Properties properties)
        throws EmailProcessingException {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            this.store = connect(username, password, properties);
        } catch (MessagingException e) {
            throw new EmailProcessingException(e);
        }
    }

    protected abstract Store connect(String username, String password,
            Properties properties) throws MessagingException;

    @Override
    public Stream<Message> getMessages(String mailbox) throws EmailProcessingException {
        try {
            folder = store.getFolder(mailbox);
            int mode = readOnly ? Folder.READ_ONLY : Folder.READ_WRITE;
            folder.open(mode);
            Message[] messages = folder.getMessages();
            return Arrays.stream(messages);
        } catch (MessagingException me) {
            throw new EmailProcessingException(me);
        }

    }

    @Override
    public void close() throws Exception {
        if (folder != null && folder.isOpen()) {
            folder.close(deleteAfterProcessing);
        }
        if (store != null && store.isConnected()) {
            store.close();
        }
    }

    public MailboxConnector deleteAfterProcessing(boolean deleteAfterProcessing) {
        this.deleteAfterProcessing = deleteAfterProcessing;
        return this;
    }

    public MailboxConnector readOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }
}
