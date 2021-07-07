package ru.chaban.model;

import ru.chaban.service.Id;

public class Account {
    @Id
    private Long no;
    private String typeVal;
    private Double rest;
    private int ver;

    public Account() {
    }

    public Account(Long no) {
        this.no = no;
        ver = 1;
    }

    public Account(Long no, String typeVal, Double rest) {
        this.no = no;
        this.typeVal = typeVal;
        this.rest = rest;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(String typeVal) {
        this.typeVal = typeVal;
    }

    public Double getRest() {
        return rest;
    }

    public void setRest(Double rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", typeVal='" + typeVal + '\'' +
                ", rest=" + rest +
                ", ver=" + ver +
                '}';
    }
}
