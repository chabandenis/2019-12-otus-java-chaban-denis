package ru.denisch.department;

import java.util.HashMap;
import java.util.Map;

public class ObserverImpl implements Observer {
    // количество денег в каждом бакомате
    private Map<Long, Long> moneyInAtm = new HashMap<>();

    @Override
    public void update(long sumChange, long idAtm) {
        if (moneyInAtm.containsKey(idAtm)) {
            moneyInAtm.put(idAtm, moneyInAtm.get(idAtm) + sumChange);
        } else {
            moneyInAtm.put(idAtm, sumChange);
        }
    }

    public Map<Long, Long> getMoneyInAtm() {
        return moneyInAtm;
    }

    public void setMoneyInAtm(Map<Long, Long> moneyInAtm) {
        this.moneyInAtm = moneyInAtm;
    }
}
