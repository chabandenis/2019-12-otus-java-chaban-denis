package ru.denisch.atm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//Memento - первоначальное состояние банкомата
public class CassetteImpl implements Cassete {

    // максимальное количество купюр в кассете
    private static int maxCount = 10;
    // номинал
    private CurTypeImpl nominal;
    // в касете хранятся банкноты, не более 10
    private Deque<BillImpl> q = new ArrayDeque(maxCount);

    public CurTypeImpl getNominal() {
        return nominal;
    }

    public void setNominal(CurTypeImpl nominal) {
        this.nominal = nominal;
    }

    public Deque<BillImpl> getQ() {
        return q;
    }

    public void setQ(Deque<BillImpl> q) {
        this.q = q;
    }

    public void newQ() {
        this.q = new ArrayDeque(maxCount);
    }

    // касету создать и передать набор купюр
    public CassetteImpl(List<BillImpl> billImpls) throws AtmException {
        this.put(billImpls);
        nominal = billImpls.get(0).getCurTypeImpl();
    }

    // пустая касета
    public CassetteImpl() {
    }

    // добавить кпюру в касету
    public void put(List<BillImpl> billImpls) throws AtmException {

        if ((billImpls.size() + q.size()) > maxCount) {
            throw new AtmException("Касета переполнена");
        }

        for (BillImpl b : billImpls) {
            q.add(b);
        }
        nominal = billImpls.get(0).getCurTypeImpl();
    }

    // взять купюру из касеты
    public List<BillImpl> get(int cnt) {
        List tmpBill = new ArrayList();
        for (int i = 0; i < cnt; i++) {
            tmpBill.add(q.getLast());
            q.removeLast();
        }
        return tmpBill;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public List<BillImpl> getStatus() {
        List<BillImpl> tmp = new ArrayList<>();
        for (BillImpl billImpl : q) {
            tmp.add(billImpl);
        }

        return tmp;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public Long getTotalSum() {
        return Long.valueOf(q.size()) * nominal.getNominal();
    }

    // купюры в касете
    public CurTypeImpl getCurType() {
        return nominal;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (BillImpl billImpl : q) {
            str.append(billImpl.toString() + "\n");
        }
        return str.toString();
    }
}
