package ru.chaban.model;

import ru.chaban.service.Id;

public class User {
    @Id
    private final Long id;
    private String name;
    private Integer age;
    private int ver;
    private Long testLong;
    private long test_long;

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ver = 1;
        this.testLong = 2L;
        this.test_long = 3L;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public Long getTestLong() {
        return testLong;
    }

    public void setTestLong(Long testLong) {
        this.testLong = testLong;
    }

    public long getTest_long() {
        return test_long;
    }

    public void setTest_long(long test_long) {
        this.test_long = test_long;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", ver=" + ver +
                ", testLong=" + testLong +
                ", test_long=" + test_long +
                '}';
    }
}
