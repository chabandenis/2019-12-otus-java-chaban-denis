package ru.denisch.atm;

import java.util.List;

public interface Cassete {

    // добавить кпюру в касету
    void put(List<Bill> bills) throws AtmException;

    // взять купюру из касеты
    List<Bill> get(int cnt);

}
