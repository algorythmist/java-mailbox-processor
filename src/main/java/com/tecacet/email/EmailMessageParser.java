package com.tecacet.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.function.Function;

public class EmailMessageParser implements Function<Message, EmailMessage> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EmailMessage apply(Message message) {

        try {
            return parse(message);
        } catch (MessagingException | IOException e) {
            throw new EmailProcessingException(e);
        }
    }

    public EmailMessage parse(Message message) throws MessagingException, IOException {
        MutableEmailMessage emailMessage = new MutableEmailMessage();
        Address[] addresses = message.getFrom();
        if (addresses != null && addresses.length > 0) {
            String from = addresses[0].toString();
            from = decodeMimeHeader(from);
            emailMessage.setFrom(from);
        }
        emailMessage.setSubject(message.getSubject());
        emailMessage.setHTMLEmail(isHtml(message));
        emailMessage.setMessage(getText(message));

        // TODO parse attachments
        return emailMessage;
    }

    private boolean isHtml(Part part) throws MessagingException {
        return part.isMimeType("text/html");
    }

    private String decodeMimeHeader(String text) throws UnsupportedEncodingException {
        return MimeUtility.decodeText(text);
    }

    /*
     * Return the primary text content of the message as text
     */
    private String getText(Part part) throws MessagingException, IOException {
        if (part.isMimeType("text/*")) {
            return (String) part.getContent();
        }
        if (part.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    String text = getText(bp);
                    if (text != null) {
                        return text;
                    }
                } else if (bp.isMimeType("text/html")) {
                    String text = getText(bp);
                    if (text != null)
                        return text;
                } else {
                    return getText(bp);
                }
            }
        } else if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String text = getText(mp.getBodyPart(i));
                if (text != null) {
                    return text;
                }
            }
        }
        logger.warn("Unrecognized Mime type");
        return null;
    }

}
