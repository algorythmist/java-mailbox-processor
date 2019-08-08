package com.tecacet.email;

import javax.mail.MessagingException;

public class EmailProcessingException extends RuntimeException {

    public EmailProcessingException(MessagingException exception) {
        super(exception);
    }
}
