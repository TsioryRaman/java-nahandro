package com.training.infrastructure.persistence.user;

import com.training.infrastructure.persistence.address.AddressJpaEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true, name = "password_hash")
    private String passwordHash;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<AddressJpaEntity> addresses = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserRoleJpaEntity> userRole = new HashSet<UserRoleJpaEntity>();

    protected UserJpaEntity() {
        // Required by JPA
    }

    public UserJpaEntity(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(List<AddressJpaEntity> addresses){
        this.addresses.addAll(addresses);
        this.addresses.forEach(a -> {
            a.setUser(this);
        });
    }

    public List<AddressJpaEntity> getAddresses() {return addresses;}

    public void addAddress(AddressJpaEntity addressJpaEntity) {
        this.addresses.add(addressJpaEntity);
        this.addresses.forEach(a -> {
            a.setUser(this);
        });
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }


    public void setAddresses(List<AddressJpaEntity> addresses) {
        this.addresses = addresses;
    }

    public Set<UserRoleJpaEntity> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleJpaEntity> userRole) {
        this.userRole = userRole;
    }
}
