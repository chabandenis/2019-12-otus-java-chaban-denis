package ru.denisch.department;

import ru.denisch.atm.Atm;

public class DoSomethingWhenSumInATMChangedTestImpl implements DoSomethingWhenSumInATMChanged {
    public void doSomething(long sumChange, Atm atm, DepAtm depAtm) {
        if (depAtm.getMoneyInAtm().containsKey(atm)) {
            depAtm.getMoneyInAtm().put(atm, depAtm.getMoneyInAtm().get(atm) + sumChange);
        } else {
            depAtm.getMoneyInAtm().put(atm, sumChange);
        }
    }
}
