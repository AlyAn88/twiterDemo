package com.example.twiterDemo.objectClasesAndRepository.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "follow")
public class Follow implements Comparable<Follow>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;


    @ManyToOne
    private User user;


    @OneToOne
    private User followThisUser;


    public Follow(User user) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime());
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public User getFollowThisUser() {
        return followThisUser;
    }

    public void setFollowThisUser(User followThisUser) {
        this.followThisUser = followThisUser;
    }

    @Override
    public int compareTo(Follow o) {
        int i =0;
        if (o.getTimestamp()!=null) {
            i = this.getTimestamp().compareTo(o.getTimestamp());
        }
        if(i==1){
            return i=-1;
        }
        if (i==-1){
            return i=1;
        }
        return i;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
