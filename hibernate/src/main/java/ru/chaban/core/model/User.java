package ru.chaban.core.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addresses")
    private Address addresses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Phone> phone;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
        this.phone = phone;
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

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User myObject = (User) obj;
        return Objects.equals(id, myObject.id) &&
                Objects.equals(name, myObject.name) &&
                ((addresses == null && myObject.addresses == null) ? true : addresses.equals(myObject.addresses)) &&
                (((phone == null && myObject.phone == null) ||
                        (phone != null && phone.size() == 0 && myObject.phone == null)
                ) ? true : phone.containsAll(myObject.phone));
    }
}
