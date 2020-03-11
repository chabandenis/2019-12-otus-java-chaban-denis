package ru.denisch.atm;

public interface Bill {

    // получить серийный номер
    String getSerNumber();

    // номинал
    CurType getCurType();


}
