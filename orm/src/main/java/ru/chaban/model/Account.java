package ru.chaban.model;

public class Account {
  private final long no;
  private final String typeVal;
  private final double rest;

  public Account(long no, String typeVal, double rest) {
    this.no = no;
    this.rest = rest;
    this.typeVal = typeVal;
  }

  public long getNo() {
    return no;
  }

  public String getTypeVal() {
    return typeVal;
  }

  public double getRest() {
    return rest;
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
