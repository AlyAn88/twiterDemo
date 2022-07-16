package com.example.twiterDemo.dtoService.dTO;

public class TwiterUsersDTO implements Comparable<TwiterUsersDTO>{
    private String userName;
    private String firstName;
    private String lastName;
    private String mail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public int compareTo(TwiterUsersDTO o) {
        int i = this.userName.compareTo(o.getUserName());
        return i;
    }
}
