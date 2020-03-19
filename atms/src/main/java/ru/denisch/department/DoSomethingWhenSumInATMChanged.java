package ru.denisch.department;

import ru.denisch.atm.Atm;

public interface DoSomethingWhenSumInATMChanged {
    void doSomething(long sumChange, Atm atm, DepAtm depAtm);
}
