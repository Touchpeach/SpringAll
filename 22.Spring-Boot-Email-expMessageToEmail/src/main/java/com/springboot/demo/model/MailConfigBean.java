package com.springboot.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * create pengtao
 **/
@ConfigurationProperties(prefix="mail.config")
public class MailConfigBean {

    private String local;

    private String sendTopic;

    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSendTopic() {
        return sendTopic;
    }

    public void setSendTopic(String sendTopic) {
        this.sendTopic = sendTopic;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
