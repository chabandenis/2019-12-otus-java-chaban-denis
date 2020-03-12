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

    // состояние по идентификатору банкомата
    private Map<Long, CaretakerMementoAtm> stateAtm = new HashMap<>();

    // количество денег в каждом бакомате
    private Observer observer = new ObserverImpl();

    public List<Atm> getAtms() {
        return atms;
    }

    public void addAtm(Atm atm) {
        this.atms.add(atm);
        atm.registerObserver(observer);
    }

    @Override
    public void saveState() {
        atms.stream().forEach((atm) -> {
                    stateAtm.put(
                            atm.getId(),
                            (new CaretakerMementoAtm()).setMementoAtm(atm.saveState())
                    );
                }
        );
    }

    @Override
    public void restoreState() {
        atms.stream().forEach((atm) -> {
                    atm.restoreState(stateAtm.get(atm.getId()).getMementoAtm());
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
