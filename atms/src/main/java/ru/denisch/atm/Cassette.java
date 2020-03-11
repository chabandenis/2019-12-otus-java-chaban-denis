package ru.denisch.atm;

import java.util.Deque;
import java.util.List;

public interface Cassette {
    // добавить кпюру в касету
    void put(List<Bill> bills) throws AtmException;

    // взять купюру из касеты
    List<Bill> get(int cnt);

    CurType getNominal();

    void setNominal(CurType nominal);

    Deque<Bill> getQ();

    void setQ(Deque<Bill> q);

    // купюры в касете
    CurType getCurType();

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    Long getTotalSum();

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    List<Bill> getStatus();

    // клонирование
    Cassette myClone();
}
