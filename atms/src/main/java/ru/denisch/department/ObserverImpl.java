package ru.denisch.department;

import ru.denisch.atm.Atm;

public class ObserverImpl implements Observer {

    private DepAtm depAtm;

    public ObserverImpl(DepAtm depAtm) {
        this.depAtm = depAtm;
    }

    @Override
    public void update(long sumChange, Atm atm) {
        DoSomethingWhenSumInATMChanged toDo = new DoSomethingWhenSumInATMChangedTestImpl();
        toDo.doSomething(sumChange, atm, depAtm);
    }
}
