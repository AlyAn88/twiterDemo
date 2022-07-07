package com.example.twiterDemo.models;

import javax.persistence.*;

@Entity
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @ManyToOne
    private Post post;

    public LikeModel(User user) {
        this.user = user;
    }

    public LikeModel() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
