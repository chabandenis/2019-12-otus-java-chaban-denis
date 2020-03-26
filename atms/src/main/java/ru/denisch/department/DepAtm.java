package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.List;
import java.util.Map;

// департамент АТМ
interface DepAtm {

    // список банкоматов
    List<Atm> getAtms();

    // добавить банкомат
    void addAtm(Atm atms);

    void saveState();

    void restoreState();

    Map<Atm, Long> getMoneyInAtm();
}
