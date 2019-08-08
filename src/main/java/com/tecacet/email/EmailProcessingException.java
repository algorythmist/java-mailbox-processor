package com.tecacet.email;

import javax.mail.MessagingException;

public class EmailProcessingException extends RuntimeException {

    public EmailProcessingException(Exception exception) {
        super(exception);
    }
}
