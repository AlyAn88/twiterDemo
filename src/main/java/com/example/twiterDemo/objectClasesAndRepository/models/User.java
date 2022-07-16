package com.example.twiterDemo.objectClasesAndRepository.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Comparable<User>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;


    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(targetEntity = Follow.class, cascade = CascadeType.ALL)
    private List<Follow> follows = new ArrayList<>();


    public User(String firstName, String lastName, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Follow> getUsersIFollow() {
        return follows;
    }

    public void setUsersIFollow(List<Follow> usersIFollow) {
        this.follows = usersIFollow;
    }

    @Override
    public int compareTo(User o) {

        int i = this.getFirstName().compareTo(o.getFirstName());
        if (i == 0) {
            i = this.getLastName().compareTo(o.getLastName());
            if (i == 0) {
                i = this.getMail().compareTo(o.getMail());
            }
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
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
