package ru.denisch.atm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CassetteImpl implements Cassette {

    // максимальное количество купюр в кассете
    private static int maxCount = 10;
    // номинал
    private CurType nominal;
    // в касете хранятся банкноты, не более 10
    private Deque<Bill> q = new ArrayDeque(maxCount);

    // касету создать и передать набор купюр
    public CassetteImpl(List<Bill> bills) throws AtmException {
        this.put(bills);
        nominal = bills.get(0).getCurType();
    }

    // пустая касета
    public CassetteImpl() {
    }

    public CurType getNominal() {
        return nominal;
    }

    public void setNominal(CurType nominal) {
        this.nominal = nominal;
    }

    public Deque<Bill> getQ() {
        return q;
    }

    public void setQ(Deque<Bill> q) {
        this.q = q;
    }

    public void newQ() {
        this.q = new ArrayDeque(maxCount);
    }

    // добавить кпюру в касету
    public void put(List<Bill> bills) throws AtmException {

        if ((bills.size() + q.size()) > maxCount) {
            throw new AtmException("Касета переполнена");
        }

        for (Bill b : bills) {
            q.add(b);
        }
        nominal = bills.get(0).getCurType();
    }

    // взять купюру из касеты
    public List<Bill> get(int cnt) {
        List tmpBill = new ArrayList();
        for (int i = 0; i < cnt; i++) {
            tmpBill.add(q.getLast());
            q.removeLast();
        }
        return tmpBill;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public List<Bill> getStatus() {
        List<Bill> tmp = new ArrayList<>();
        for (Bill bill : q) {
            tmp.add(bill);
        }

        return tmp;
    }

    // функция служебная. Аналог окрыть банкомат и посмотреть, а что в касете лежит, в какой последовательности и с какими серийными номерами
    public Long getTotalSum() {
        return Long.valueOf(q.size()) * nominal.getNominal();
    }

    // купюры в касете
    public CurType getCurType() {
        return nominal;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (Bill bill : q) {
            str.append(bill.toString() + "\n");
        }
        return str.toString();
    }
}
