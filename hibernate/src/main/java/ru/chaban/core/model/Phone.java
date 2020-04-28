package ru.chaban.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    private User user;

    public Phone() {
    }

    public Phone(long id, String number) {
        this.id = id;
        this.number = number;
    }
}
