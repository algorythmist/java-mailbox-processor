package com.tecacet.email;

import javax.mail.Message;
import java.util.stream.Stream;

/**
 * Utility to read messages from a mailbox
 */
public interface MailboxConnector extends AutoCloseable {

    /**
     * Get all messages in a given mailbox
     *
     * @param mailbox the name of the mailbox
     * @return a stream of email messages
     */
    Stream<Message> getMessages(String mailbox);

    default Stream<EmailMessage> getParsedMessages(String mailbox) {
        return getMessages(mailbox).map(new EmailMessageParser());
    }
}
