package com.codegym.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String address;
    private Boolean gender;

    public User(){};

    public User(String name, String email, String address, Boolean gender) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    public User(int id, String name, String email, String address, Boolean gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
