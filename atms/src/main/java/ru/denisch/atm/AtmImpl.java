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
        return null;
    }

    // функция загрузки касет.
    // в функции за один раз должны быть передаваться номиналы одного достоинства
    // инициализация в обратном порядке
    public AtmImpl loadCassetes(List<BillImpl> billImpls) throws AtmException {
        // сформировали касету
        CassetteImpl cassetteImpl = new CassetteImpl(billImpls);
        // запомнили соответствие купюра- касета
        ct.getCasseteForMoney().put(billImpls.get(0).getCurTypeImpl().getNominal(), cassetteImpl);
        // запонили сколько в касете денег
        ct.getCntInCassete().put(cassetteImpl, (long) billImpls.size());

        ct.getCassetteImpls().add(cassetteImpl);
        return this;
    }

    // user add money
    public long addMoney(List<BillImpl> money) throws AtmException {
        long retValue = 0;

        for (BillImpl billImpl : money) {
            CassetteImpl cassetteImpl = ct.getCasseteForMoney().get(billImpl.getCurTypeImpl().getNominal());
            List<BillImpl> tmpList = new ArrayList<>();
            tmpList.add(billImpl);
            cassetteImpl.put(tmpList);

            // запонили сколько в касете денег
            ct.getCntInCassete().put(cassetteImpl, ct.getCntInCassete().get(cassetteImpl) + 1);


            retValue += billImpl.getCurTypeImpl().getNominal();
        }

        return retValue;
    }

    // служебная функция, возвращает номиналы в касетах
    public List<BillImpl> status() {
        List<BillImpl> tmp = new ArrayList<>();

        for (CassetteImpl cassetteImpl : ct.getCassetteImpls()) {
            tmp.addAll(cassetteImpl.getStatus());
        }

        return tmp;
    }

    @Override
    public String toString() {
        String tmpStatus = "";

        for (CassetteImpl cassetteImpl : ct.getCassetteImpls()) {
            tmpStatus += cassetteImpl.toString() + "; " + ct.getCntInCassete().get(cassetteImpl) + "\n";
        }

        return tmpStatus;
    }


    public List<BillImpl> getMoney(long value) throws AtmException {
        List<BillImpl> tmpBillImpls = new ArrayList<>();

        long retMoney = value;

        for (CassetteImpl cassetteImpl : ct.getCassetteImpls()) {
//            System.out.println("касета " + cassetteNew.toString());

            long price = cassetteImpl.getCurType().getNominal();


            if (price <= value) {
                // есть купюры
                // денег для списания больше, чем купюра в кассете
                // остаток весь списан
                while (cassetteImpl.getCurType().getNominal() <= retMoney && retMoney != 0 && ct.getCntInCassete().get(cassetteImpl) > 0) {
                    List oneBill = new ArrayList();
                    oneBill = cassetteImpl.get(1);
                    tmpBillImpls.addAll(oneBill);
                    // запонили сколько в касете денег
                    ct.getCntInCassete().put(cassetteImpl, ct.getCntInCassete().get(cassetteImpl) - 1);
                    retMoney -= cassetteImpl.getCurType().getNominal();
                }
            }


        }

        int countMoney = 0;

        // посмотрим, есть ли в банкомате деньги
        for (CassetteImpl cassetteImpl : ct.getCassetteImpls()) {
            countMoney += ct.getCntInCassete().get(cassetteImpl);
        }

        if (retMoney > 0 && countMoney > 0) {
            throw new AtmException("Подходящие купюры отсутствуют в банкомате");
        }

        if (retMoney > 0 && countMoney == 0) {
            throw new AtmException("Запрашиваемая сумма больше остатка в банкомате");
        }

        return tmpBillImpls;
    }

    public MementoAtm saveState() {
        return new MementoAtm(ct);
    }

    public void restoreState(MementoAtm memento) {
        this.ct = memento.getCt();
    }

}
