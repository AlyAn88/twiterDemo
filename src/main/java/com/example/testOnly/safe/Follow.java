package com.example.twiterDemo.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToMany (targetEntity = User.class)
    private Set<User> following = new HashSet<>();

    private Long timestamp;

    //Date date = new Date();
   // Timestamp ts = Timestamp.from(Instant.now());

    public Follow(User user) {
        this.user = user;

    }

    public Follow() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        Timestamp ts = Timestamp.from(ZonedDateTime.now().toInstant());
        this.timestamp = ts.getTime();
        this.following = following;
    }

    public Long getTimestamp() {
        return timestamp;
    }

   /* public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }*/
}
