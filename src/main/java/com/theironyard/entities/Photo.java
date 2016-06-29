package com.theironyard.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoseasandstrom on 6/28/16.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User sender;

    @ManyToOne
    User recipient;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    Date currentTime;

    @Column(nullable = false)
    Date actualExpiredTime;

    @Column(nullable = false)
    Date modifiedTime;

    @Column(nullable = false)
    Boolean modTime;

    @Column(nullable = false)
    Boolean makePublic;

    public Photo() {
    }

    public Photo(User sender, User recipient, String filename) {
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
    }

    public Photo(int id) {
        this.id = id;
    }

    public Photo(String filename, Date actualExpiredTime) {
        this.filename = filename;
        this.actualExpiredTime = actualExpiredTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Date getActualExpiredTime() {
        return actualExpiredTime;
    }

    public void setActualExpiredTime(Date actualExpiredTime) {
        this.actualExpiredTime = actualExpiredTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getMakePublic() {
        return makePublic;
    }

    public void setMakePublic(Boolean makePublic) {
        this.makePublic = makePublic;
    }
}
