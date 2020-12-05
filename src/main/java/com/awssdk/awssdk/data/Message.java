package com.awssdk.awssdk.data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sender")
    private String sender;
    @Column(name = "message")
    private String messaage;
    @Column(name = "sentTime")
    private Date sentTime;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessaage() {
        return messaage;
    }

    public void setMessaage(String messaage) {
        this.messaage = messaage;
    }

    public Date getSentTime(Date date) {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}
