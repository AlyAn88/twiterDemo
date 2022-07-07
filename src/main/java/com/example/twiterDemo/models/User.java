package com.example.twiterDemo.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Comparable<User>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 255)
    private String firstName;


    @Column(name = "lastName", nullable = false, length = 255)
    private String lastName;

    @Column(name = "mail", nullable = false, length = 255)
    private String mail;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
