package com.neux.spring.service.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MailInfo {
    private List<String> recipients = new ArrayList<>();
    private String subject = null;
    private String body = null;
    private List<File> attachFiles = new ArrayList<>();

    public MailInfo() {}

    public MailInfo(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public void addRecipient(String recipient) {
        this.recipients.add(recipient);
    }

    public void addAttach(File attach) {
        attachFiles.add(attach);
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<File> getAttachFiles() {
        return attachFiles;
    }

    public String toRecipient() {
        return String.join(";",recipients);
    }

    public String[] toRecipientArray() {
        return recipients.toArray(new String[recipients.size()]);
    }

    public String toString() {
        return "Subject["+subject+"],body["+body+"],recipients["+toRecipient()+"]";
    }
}
