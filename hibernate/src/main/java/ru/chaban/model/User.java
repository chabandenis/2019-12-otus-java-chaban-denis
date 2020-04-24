package ru.chaban.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    private String fio;
    private AddressDataSet addressDataSet;
    private PhoneDataSet phi;
}
