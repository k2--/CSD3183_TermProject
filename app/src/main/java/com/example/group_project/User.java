package com.example.group_project;

public class User {

    int id;
    String email, password, security;

    public User(int id, String email, String password, String security) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.security = security;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
