package com.example.twiterDemo;

//import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.User;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Mention {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private User user;
    //private Post post;

    public Mention() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   /* public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }*/
}
