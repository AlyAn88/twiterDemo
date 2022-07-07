package com.example.twiterDemo.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Post implements Comparable<Post>  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "messaje", nullable = false, length = 200)
    private String message;

    @Column(name = "timestamp", nullable = false, length = 200)
    private Long timestamp;

    @ManyToOne
    private User user;

    @OneToMany (targetEntity = Reply.class)
    private Set<Reply> replies = new TreeSet<>();



    public Post( String message) {
        this.message = message;
        Timestamp ts = Timestamp.from(ZonedDateTime.now().toInstant());
        this.timestamp = ts.getTime();
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        Timestamp ts = Timestamp.from(ZonedDateTime.now().toInstant());
        this.timestamp = ts.getTime();
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public int compareTo(Post o) {
        int i = this.getTimestamp().compareTo(o.getTimestamp());
        return i;
    }
   /* public void setTimestamp() {
        Timestamp ts = Timestamp.from(ZonedDateTime.now().toInstant());
        this.timestamp = ts.getTime();
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }*/
}
