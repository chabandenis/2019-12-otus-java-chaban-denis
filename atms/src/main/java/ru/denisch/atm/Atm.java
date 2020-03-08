package ru.denisch.atm;

import java.util.List;

public interface Atm {

    // положить деньги в банкомат
    long addMoney(List<BillImpl> money) throws AtmException;

    // взять деньги
    List<BillImpl> getMoney(long value) throws AtmException;

    MementoAtm saveState();

    void restoreState(MementoAtm memento);

    // идентификатор банкомата
    Long getId();
}
