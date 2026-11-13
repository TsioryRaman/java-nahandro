package com.training.entity;

import java.util.List;

public class User {
    private Long id;
    private String email;
    private String username;
    private List<Address> addresses;
    private String passwordHash;

    public User(Long id, String email, String username, List<Address> addresses) {
        this.id = id;
        this.username = username;
        setAddresses(addresses);
        setEmail(email); // Logique métier potentielle (validation)
    }

    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalide");
        }
        this.email = email;
    }
    public void setAddresses(List<Address> addresses){
//        if(addresses.isEmpty())
//            throw new IllegalArgumentException("Address is empty");
        this.addresses = addresses;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getUsername() {return username;}
    public List<Address> getAddresses() {return addresses;}
}
