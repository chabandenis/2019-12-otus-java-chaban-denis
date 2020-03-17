package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.Map;

public interface Observer {
    // сумма измнений в банкомате
    void update(long sumChange, Atm atm);

    Map<Long, Long> getMoneyInAtm();

    void setMoneyInAtm(Map<Long, Long> moneyInAtm);
}
