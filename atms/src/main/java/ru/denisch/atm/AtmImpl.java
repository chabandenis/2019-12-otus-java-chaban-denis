package ru.denisch.atm;

import java.util.ArrayList;
import java.util.List;

public class AtmImpl implements Atm {
    //
    private ComplexType4MementoAtm ct = new ComplexType4MementoAtm();

    // идентификатор банкомата
    private Long id;

    // конструктор обязательно должен быть определен ID
    public AtmImpl(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    // количество денег в банкомате
    public Long totalSum() {
        Long tmpSum = Long.valueOf(0);
        for (Cassette cassette : ct.getCassette()) {
            tmpSum += cassette.getTotalSum();
        }
        return tmpSum;
    }

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    public AtmImpl loadCassettes(List<Bill> bills) throws AtmException {
        // сформировали касету
        Cassette cassette = new CassetteImpl(bills);
        // запомнили соответствие купюра- касета
        ct.getCassetteForMoney().put(bills.get(0).getCurType().getNominal(), cassette);
        // запонили сколько в касете денег
        ct.getCntInCassette().put(cassette, (long) bills.size());

        ct.getCassette().add(cassette);
        return this;
    }

    // user add money
    public long addMoney(List<Bill> money) throws AtmException {
        long retValue = 0;

        for (Bill bill : money) {
            Cassette cassette = ct.getCassetteForMoney().get(bill.getCurType().getNominal());
            List<Bill> tmpList = new ArrayList<>();
            tmpList.add(bill);
            cassette.put(tmpList);

            // запонили сколько в касете денег
            ct.getCntInCassette().put(cassette, ct.getCntInCassette().get(cassette) + 1);


            retValue += bill.getCurType().getNominal();
        }

        return retValue;
    }

    // служебная функция, возвращает номиналы в касетах
    public List<Bill> status() {
        List<Bill> tmp = new ArrayList<>();

        for (Cassette cassette : ct.getCassette()) {
            tmp.addAll(cassette.getStatus());
        }

        return tmp;
    }

    @Override
    public String toString() {
        String tmpStatus = "";

        for (Cassette cassette : ct.getCassette()) {
            tmpStatus += cassette.toString() + "; " + ct.getCntInCassette().get(cassette) + "\n";
        }

        return tmpStatus;
    }

    public List<Bill> getMoney(long value) throws AtmException {
        List<Bill> bills = new ArrayList<>();

        long retMoney = value;

        for (Cassette cassette : ct.getCassette()) {

            long price = cassette.getCurType().getNominal();


            if (price <= value) {
                // есть купюры
                // денег для списания больше, чем купюра в кассете
                // остаток весь списан
                while (cassette.getCurType().getNominal() <= retMoney && retMoney != 0 && ct.getCntInCassette().get(cassette) > 0) {
                    List oneBill = new ArrayList();
                    oneBill = cassette.get(1);
                    bills.addAll(oneBill);
                    // запонили сколько в касете денег
                    ct.getCntInCassette().put(cassette, ct.getCntInCassette().get(cassette) - 1);
                    retMoney -= cassette.getCurType().getNominal();
                }
            }
        }

        int countMoney = 0;

        // посмотрим, есть ли в банкомате деньги
        for (Cassette cassette : ct.getCassette()) {
            countMoney += ct.getCntInCassette().get(cassette);
        }

        if (retMoney > 0 && countMoney > 0) {
            throw new AtmException("Подходящие купюры отсутствуют в банкомате");
        }

        if (retMoney > 0 && countMoney == 0) {
            throw new AtmException("Запрашиваемая сумма больше остатка в банкомате");
        }

        return bills;
    }

    public MementoAtm saveState() {
        return new MementoAtm(ct);
    }

    public void restoreState(MementoAtm memento) {
        this.ct = memento.getCt();
    }

}
