package ru.denisch.atm;

import java.util.List;

public interface Atm {

    // положить деньги в банкомат
    long addMoney(List<Bill> money) throws AtmException;

    // взять деньги
    List<BillImpl> getMoney(long value) throws AtmException;

    // количество денег в банкомате
    Long totalSum();

    MementoAtm saveState();

    void restoreState(MementoAtm memento);

    // идентификатор банкомата
    Long getId();
}
