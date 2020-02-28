package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.ArrayList;
import java.util.List;

public class DepAtmImpl implements DepAtm {
    List<Atm> atms = new ArrayList<>();

    public List<Atm> getAtms() {
        return atms;
    }

    public void addAtm(Atm atm) {
        atms.add(atm);
    }
}
