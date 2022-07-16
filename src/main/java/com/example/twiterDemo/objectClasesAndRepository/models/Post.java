package com.example.twiterDemo.objectClasesAndRepository.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "post")
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false, length = 200)
    private String message;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    private User user;

    @OneToMany(targetEntity = Reply.class, cascade = CascadeType.ALL)
    private List<Reply> postReplies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Reply replyToPost;

    @OneToMany(targetEntity = Like.class, cascade = CascadeType.ALL)
    private List<Like> whoLikeYourPost = new ArrayList<>();

    public Post(String message, User user) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date = new Date();

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public List<Reply> getReplies() {
        return postReplies;
    }

    public List<Like> getWhoLikeYourPost() {
        return whoLikeYourPost;
    }

    public Reply getReplyToPost() {
        return replyToPost;
    }

    public void setReplyToPost(Reply reply) {
        this.replyToPost = reply;
    }

    @Override
    public int compareTo(Post o) {
        int i = this.getTimestamp().compareTo(o.getTimestamp());
        if (i == 1) {
            return i = -1;
        }
        if (i == -1) {
            return i = 1;
        }
        return i;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }

}
