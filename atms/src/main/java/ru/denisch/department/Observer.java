package ru.denisch.department;

import ru.denisch.atm.Atm;

public interface Observer {
    // сумма измнений в банкомате
    void update(long sumChange, Atm atm);
}
