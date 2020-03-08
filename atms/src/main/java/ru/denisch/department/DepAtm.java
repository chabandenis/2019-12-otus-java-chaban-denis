package ru.denisch.department;

import ru.denisch.atm.Atm;

import java.util.List;

// департамент АТМ
interface DepAtm {

    // список банкоматов
    List<Atm> getAtms();

    // добавить банкомат
    void addAtm(Atm atms);

    void saveState();

    void restoreState();
}
