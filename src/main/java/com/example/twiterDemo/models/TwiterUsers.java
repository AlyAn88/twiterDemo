package com.example.twiterDemo.models;

//import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@EntityScan("com.example.twiterDemo.models")
@Entity
@Table(name = "twiterUsers")
public class TwiterUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false, length = 255)
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    private Set<Post> posts = new TreeSet<>();



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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setUser(User user) {

        this.user = user;

       /* Field nameField = this.getClass().getDeclaredField("user");
        nameField.setAccessible(true);
        nameField.set(this.user,userName);*/
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
