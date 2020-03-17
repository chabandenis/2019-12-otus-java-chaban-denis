package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.HashMap;
import java.util.Map;

public class DoSomethingWhenSumInATMChangedTestImpl implements DoSomethingWhenSumInATMChanged {

    // для тестирования
    // количество денег в каждом бакомате
    private static Map<Long, Long> moneyInAtm = new HashMap<>();

    public static Map<Long, Long> getMoneyInAtm() {
        return moneyInAtm;
    }

    public void doSomething(long sumChange, Atm atm) {
        if (moneyInAtm.containsKey(atm.getId())) {
            moneyInAtm.put(atm.getId(), moneyInAtm.get(atm.getId()) + sumChange);
        } else {
            moneyInAtm.put(atm.getId(), sumChange);
        }
    }
}
