package ru.chaban.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;

    @Column(name = "street")
    private String street;

    @OneToOne(mappedBy="addresses")
    private User user;

    public Address() {
    }

    public Address(long id, String street) {
        Id = id;
        this.street = street;
    }
}
