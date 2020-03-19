package ru.denisch.department;

import ru.denisch.atm.Atm;
import ru.denisch.atm.CaretakerMementoAtm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepAtmImpl implements DepAtm {
    // банкоматы подразделения
    private List<Atm> atms = new ArrayList<>();

    // количество денег в каждом бакомате
    private Map<Atm, Long> moneyInAtm = new HashMap<>();
    // состояние по идентификатору банкомата
    private Map<Long, CaretakerMementoAtm> savedPreviousState = new HashMap<>();
    // количество денег в каждом бакомате
    private Observer observer = new ObserverImpl(this);

    public Map<Atm, Long> getMoneyInAtm() {
        return moneyInAtm;
    }

    public void setMoneyInAtm(Map<Atm, Long> moneyInAtm) {
        this.moneyInAtm = moneyInAtm;
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public void addAtm(Atm atm) {
        this.atms.add(atm);
        atm.registerObserver(observer);
    }

    @Override
    public void saveState() {
        for (var atm : atms) {
            savedPreviousState.put(
                    atm.getId(),
                    (new CaretakerMementoAtm()).setMementoAtm(atm.saveState())
            );
        }
    }

    @Override
    public void restoreState() {
        atms.stream().forEach((atm) -> {
                    atm.restoreState(savedPreviousState.get(atm.getId()).getMementoAtm());
                }
        );
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
