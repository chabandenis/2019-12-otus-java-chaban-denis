package ru.chaban.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneDataSet {
    @Id
    private Long id;
    private String number;
}
