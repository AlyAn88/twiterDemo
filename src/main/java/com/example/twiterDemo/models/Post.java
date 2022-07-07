package com.example.twiterDemo.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "post")
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false, length = 200)
    private String message;

    @Column(name = "timestamp")
    public Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(targetEntity = Reply.class, cascade = CascadeType.ALL)
    private Set<Reply> replies = new TreeSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Reply reply;


    public Post(String message, User user) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
        Date date = new Date();
        // Timestamp timestamp2 = new Timestamp(date.getTime());
        // this.timestamp = timeFormat.format(timestamp2);
        this.timestamp = new Timestamp(date.getTime());
        this.message = message;
        this.user = user;
    }

    public Post() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
//        Timestamp ts = Timestamp.from(ZonedDateTime.now().toInstant());
//        this.timestamp = ts.getTime();
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public int compareTo(Post o) {
        int i = this.getTimestamp().compareTo(o.getTimestamp());
        return i;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", reply=" + reply +
                '}';
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
