package ru.chaban.model;

public class Account {
    private final Long no;
    private String typeVal;
    private Double rest;

    public Account(Long no) {
        this.no = no;
    }

    public Account(Long no, String typeVal, Double rest) {
        this.no = no;
        this.typeVal = typeVal;
        this.rest = rest;
    }

    public Long getNo() {
        return no;
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
                '}';
    }
}
