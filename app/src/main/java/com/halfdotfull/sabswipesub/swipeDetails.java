package com.halfdotfull.sabswipesub;

/**
 * Created by 15103068 on 15-04-2017.
 */

public class swipeDetails {
    String topic;
    String subject;
    String name;

    public String getTopicName() {
        return topic;
    }

    public void setTopicName(String topicName) {
        this.topic = topicName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProfname() {
        return name;
    }

    public void setProfname(String profname) {
        this.name = profname;
    }

    public swipeDetails() {

    }

    public swipeDetails(String topicName, String subject, String profname) {
        this.topic = topicName;
        this.subject = subject;
        this.name = profname;
    }
}
