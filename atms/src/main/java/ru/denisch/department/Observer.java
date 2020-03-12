package ru.denisch.department;

import java.util.Map;

public interface Observer {
    // сумма измнений в банкомате
    void update(long sumChange, long idAtm);

    Map<Long, Long> getMoneyInAtm();

    void setMoneyInAtm(Map<Long, Long> moneyInAtm);
}
