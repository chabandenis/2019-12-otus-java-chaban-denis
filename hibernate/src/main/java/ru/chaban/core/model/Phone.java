package ru.chaban.core.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "number")
    private String number;

//    @ManyToOne
//    private User user;

    public Phone() {
    }

    public Phone(long id, String number) {
        this.id = id;
        this.number = number;
 //       this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 */


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Phone myObject = (Phone) obj;
        return Objects.equals(id, myObject.id) &&
                Objects.equals(number, myObject.number);
    }


}
