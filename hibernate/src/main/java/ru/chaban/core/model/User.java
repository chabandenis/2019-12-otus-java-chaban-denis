package ru.chaban.core.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


/*
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "addresses")
    private AddressDataSet addresses;

    @Column(name = "phones")
    private PhoneDataSet phones;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDataSet getAddresses() {
        return addresses;
    }

    public void setAddresses(AddressDataSet addresses) {
        this.addresses = addresses;
    }

    public PhoneDataSet getPhones() {
        return phones;
    }

    public void setPhones(PhoneDataSet phones) {
        this.phones = phones;
    }

 */

