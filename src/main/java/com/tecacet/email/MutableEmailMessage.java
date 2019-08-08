package com.tecacet.email;


import javax.activation.DataSource;

public class MutableEmailMessage implements EmailMessage {

    private String from;
    private String subject;
    private String message;
    private String[] to;
    private String[] bcc;
    private String[] filenames = new String[0];
    private DataSource[] attachments = new DataSource[0];
    private boolean isHTMLEmail = false;

    @Override
    public String[] getRecipients() {
        return to;
    }

    @Override
    public String[] getBCCRecipients() {
        return bcc;
    }

    @Override
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }

    @Override
    public DataSource[] getDataSources() {
        return this.attachments;
    }

    @Override
    public boolean isHTMLEmail() {
        return isHTMLEmail;
    }

    public void setHTMLEmail(boolean isHTMLEmail) {
        this.isHTMLEmail = isHTMLEmail;
    }

    @Override
    public boolean hasFilenames() {
        return filenames != null && filenames.length > 0;
    }

    @Override
    public boolean hasDataSources() {
        return attachments != null && attachments.length > 0;
    }

    @Override
    public String toString() {
        return message;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public void setAttachments(DataSource[] attachments) {
        this.attachments = attachments;
    }

}
