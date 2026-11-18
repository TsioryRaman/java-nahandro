package com.training.infrastructure.persistence.address;

import com.training.infrastructure.persistence.user.UserJpaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class AddressJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected AddressJpaEntity(){

    }

    public AddressJpaEntity(String name){
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }
}
