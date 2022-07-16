package com.example.twiterDemo.objectClasesAndRepository.models;

//import com.example.twiterDemo.models.Post;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EntityScan("com.example.twiterDemo.models")
@Entity
@Table(name = "twiterUsers")
public class TwiterUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();


    public TwiterUsers(String userName, User user) {
        this.userName=userName;
        this.user=user;
    }
    public TwiterUsers(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setUser(User user) {

        this.user = user;
    }

    @Override
    public String toString() {
        return "TwiterUsers{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", user=" + user +
                '}';
    }
}
