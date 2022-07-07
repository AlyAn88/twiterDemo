package com.example.twiterDemo.models;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    private Set<User> Users = new TreeSet<>();


    public Follow(User user, Set<User> users) {
        this.user = user;
        Users = users;
    }

    public Follow() {
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

    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }


    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
