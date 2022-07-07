package com.example.twiterDemo.models;

import javax.persistence.*;

@Entity
public class Reply implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post parent;
    private Boolean onlyYou;

    public Reply(Post parent, Boolean onlyYou) {
        this.parent = parent;
        this.onlyYou = onlyYou;
    }

    public Reply() {
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Boolean getOnlyYou() {
        return onlyYou;
    }

    public void setOnlyYou(Boolean onlyYou) {
        this.onlyYou = onlyYou;
    }
    @Override
    public int compareTo(Post o) {
        int i = this.parent.getTimestamp().compareTo(o.getTimestamp());
        return i;
    }
}
