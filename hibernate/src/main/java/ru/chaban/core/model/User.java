package ru.chaban.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
