package ru.denisch.department;

import ru.denisch.atm.Atm;

public class ObserverImpl implements Observer {

    @Override
    public void update(long sumChange, Atm atm) {
        DoSomethingWhenSumInATMChanged toDo = new DoSomethingWhenSumInATMChangedTestImpl();
        toDo.doSomething(sumChange, atm);
        //System.out.println("В банкомате " + atm.getId() + " изменилась сумма на " + sumChange);
    }

}
