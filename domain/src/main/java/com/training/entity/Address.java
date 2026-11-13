package com.training.entity;

public class Address {
    private Long id;
    private String name;
    private User user;

    public Address(String name){

        this.name = name;
    }

    public String getName() {return name;}
    public User getUser() {return user;}
}
