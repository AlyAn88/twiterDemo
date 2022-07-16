package com.example.twiterDemo.objectClasesAndRepository.models;

import javax.persistence.*;

@Entity
@Table(name = "reply")
public class Reply implements Comparable<Reply> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Post post;

    private Boolean onlyYou;

    public Reply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int i =0;
        if (o.getPost().getTimestamp()!=null) {
            i = this.post.getTimestamp().compareTo(o.getPost().getTimestamp());
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
        return "Reply{" +
                "id=" + id +
                ", post=" + post +
                ", onlyYou=" + onlyYou +
                '}';
    }
}
