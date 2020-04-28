package ru.chaban.core.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "street")
    private String street;

    @OneToOne(mappedBy = "addresses")
    private User user;

    public Address() {
    }

    public Address(long id, String street, User user) {
        this.id = id;
        this.street = street;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address myObject = (Address) obj;
        return id == myObject.id &&
                Objects.equals(street, myObject.street) &&
                user.getId() == myObject.user.getId();
    }
}
