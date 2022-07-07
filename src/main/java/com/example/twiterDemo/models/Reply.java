package com.example.twiterDemo.models;

import javax.persistence.*;

@Entity
@Table(name = "reply")
public class Reply implements Comparable<Reply> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Post post;

    private Boolean onlyYou;

    public Reply(Post parent, Boolean onlyYou) {
        this.post = parent;
        this.onlyYou = onlyYou;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reply() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Boolean getOnlyYou() {
        return onlyYou;
    }

    public void setOnlyYou(Boolean onlyYou) {
        this.onlyYou = onlyYou;
    }

    @Override
    public int compareTo(Reply o) {
        int i = this.post.getTimestamp().compareTo(o.getPost().getTimestamp());
        return i;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", post=" + post +
                ", onlyYou=" + onlyYou +
                '}';
    }
}
