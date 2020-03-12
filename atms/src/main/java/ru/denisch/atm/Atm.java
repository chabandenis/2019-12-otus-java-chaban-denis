package ru.denisch.atm;

import ru.denisch.department.Observer;

import java.util.List;

public interface Atm {

    // положить деньги в банкомат
    long addMoney(List<Bill> money) throws AtmException;

    // взять деньги
    List<Bill> getMoney(long value) throws AtmException;

    // количество денег в банкомате
    Long totalSum();

    MementoAtm saveState();

    void restoreState(MementoAtm memento);

    // идентификатор банкомата
    Long getId();

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    AtmImpl loadCassettes(List<Bill> bills) throws AtmException;

    // служебная функция, возвращает номиналы в касетах
    List<Bill> status();

    // регистрируем слушателя
    void registerObserver(Observer o);
}

