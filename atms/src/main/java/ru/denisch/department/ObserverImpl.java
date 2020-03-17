package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.HashMap;
import java.util.Map;

public class ObserverImpl implements Observer {
    // количество денег в каждом бакомате
    private Map<Long, Long> moneyInAtm = new HashMap<>();

    @Override
    public void update(long sumChange, Atm atm) {
        if (moneyInAtm.containsKey(atm.getId())) {
            moneyInAtm.put(atm.getId(), moneyInAtm.get(atm.getId()) + sumChange);
        } else {
            moneyInAtm.put(atm.getId(), sumChange);
        }
    }

    public Map<Long, Long> getMoneyInAtm() {
        return moneyInAtm;
    }

    public void setMoneyInAtm(Map<Long, Long> moneyInAtm) {
        this.moneyInAtm = moneyInAtm;
    }
}
