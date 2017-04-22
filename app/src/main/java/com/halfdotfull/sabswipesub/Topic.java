package com.halfdotfull.sabswipesub;

/**
 * Created by 15103068 on 15-04-2017.
 */

public class Topic {
    String branch;
    int year;
    String topic,subject;
    String name;

    public Topic(String branch, int year, String topic, String subject, String name) {
        this.branch = branch;
        this.year = year;
        this.topic = topic;
        this.subject = subject;
        this.name = name;
    }

    public Topic() {
    }

    public String getBranch() {
        return branch;
    }

    public int getYear() {
        return year;
    }

    public String getTopic() {
        return topic;
    }

    public String getSubject() {
        return subject;
    }

    public Topic(String branch, int year, String topic,String subject) {
        this.branch = branch;
        this.year = year;
        this.topic = topic;
        this.subject=subject;
    }
}
