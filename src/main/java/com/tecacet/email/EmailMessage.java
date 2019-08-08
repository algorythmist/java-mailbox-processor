package com.tecacet.email;

import javax.activation.DataSource;


public interface EmailMessage {

    /**
     * Returns the array of recipients
     * 
     * @return List of email addresses
     */
    String[] getRecipients();

    /**
     * Returns the array of recipients
     * 
     * @return List of bcc addresses
     */
    String[] getBCCRecipients();

    /**
     * gets the from address
     * 
     * @return From email address
     */
    String getFrom();

    /**
     * Gets the subject
     * 
     * @return The email subject
     */
    String getSubject();

    /**
     * Gets the message
     * 
     * @return The email content
     */
    String getMessage();

    /**
     * Get file names
     * 
     * @return The email attachment filenames
     */
    String[] getFilenames();

    DataSource[] getDataSources();

    boolean isHTMLEmail();

    boolean hasFilenames();

    boolean hasDataSources();

    String toString();

}
