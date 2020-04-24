package ru.chaban.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AddressDataSet {
    @Id
    private Long Id;
    private String street;

}
