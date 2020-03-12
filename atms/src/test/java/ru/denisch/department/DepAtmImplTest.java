package ru.denisch.department;

import org.junit.jupiter.api.Test;
import ru.denisch.atm.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepAtmImplTest {
    @Test
        // проверяю, что мементо работает
    void department() throws AtmException {
        DepAtm depAtm = new DepAtmImpl();

        // инициализация первоначального состояния банкомата
        for (int i = 1; i <= 3; i++) {

            List<Bill> bills50 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills50.add(new BillImpl("r50 n0" + i + j, CurTypeImpl.RUR50));
            }

            List<Bill> bills100 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills100.add(new BillImpl("r100 n0" + i + j, CurTypeImpl.RUR100));
            }

            List<Bill> bills500 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills500.add(new BillImpl("r500 n0" + i + j, CurTypeImpl.RUR500));
            }

            // доабавить банкомат в департамент
            depAtm.addAtm(new AtmImpl(i - 1).loadCassettes(bills50).loadCassettes(bills100).loadCassettes(bills500));
        }

        // проверю суммы в банкоматах
        assertEquals(Long.valueOf(650), depAtm.getAtms().get(0).totalSum());
        assertEquals(Long.valueOf(1300), depAtm.getAtms().get(1).totalSum());
        assertEquals(Long.valueOf(1950), depAtm.getAtms().get(2).totalSum());

        // запомнить состояние банкоматов в департаменте
        depAtm.saveState();

        List<Bill> bill = new ArrayList<>();
        bill.add(new BillImpl("xxx", CurTypeImpl.RUR50));

        // добавлю по 50р в банкоматы
        depAtm.getAtms().stream().forEach((atm) -> {
            try {
                atm.addMoney(bill);
            } catch (AtmException e) {
                e.printStackTrace();
            }
        });

        // проверю суммы в банкоматах после увеличения на 50р
        assertEquals(Long.valueOf(650 + 50), depAtm.getAtms().get(0).totalSum());
        assertEquals(Long.valueOf(1300 + 50), depAtm.getAtms().get(1).totalSum());
        assertEquals(Long.valueOf(1950 + 50), depAtm.getAtms().get(2).totalSum());

        // вернуть исходное состояние банкоматов в департаменте
        depAtm.restoreState();

        // проверю суммы в банкоматах
        assertEquals(Long.valueOf(650), depAtm.getAtms().get(0).totalSum());
        assertEquals(Long.valueOf(1300), depAtm.getAtms().get(1).totalSum());
        assertEquals(Long.valueOf(1950), depAtm.getAtms().get(2).totalSum());
    }

    @Test
        // проверяю, что observer работает
    void observer() throws AtmException {
        DepAtm depAtm = new DepAtmImpl();

        // инициализация первоначального состояния банкомата
        for (int i = 1; i <= 3; i++) {

            List<Bill> bills50 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills50.add(new BillImpl("r50 n0" + i + j, CurTypeImpl.RUR50));
            }

            List<Bill> bills100 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills100.add(new BillImpl("r100 n0" + i + j, CurTypeImpl.RUR100));
            }

            List<Bill> bills500 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills500.add(new BillImpl("r500 n0" + i + j, CurTypeImpl.RUR500));
            }

            Atm atm = new AtmImpl(i - 1);
            depAtm.addAtm(atm);

            // доабавить банкомат в департамент
            atm.loadCassettes(bills50).loadCassettes(bills100).loadCassettes(bills500);
        }

        depAtm.getObserver().getMoneyInAtm().keySet().stream().
                forEach((idAtm) -> System.out.println("res: " + idAtm + "; " + depAtm.getObserver().getMoneyInAtm().get(idAtm)));

        // проверю суммы в банкоматах
        assertEquals(Long.valueOf(650), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(0)));
        assertEquals(Long.valueOf(1300), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(1)));
        assertEquals(Long.valueOf(1950), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(2)));

        List<Bill> bill = new ArrayList<>();
        bill.add(new BillImpl("xxx", CurTypeImpl.RUR50));

        // добавлю по 50р в банкоматы
        depAtm.getAtms().stream().forEach((atm) -> {
            try {
                atm.addMoney(bill);
            } catch (AtmException e) {
                e.printStackTrace();
            }
        });

        depAtm.getObserver().getMoneyInAtm().keySet().stream().
                forEach((idAtm) -> System.out.println("res: " + idAtm + "; " + depAtm.getObserver().getMoneyInAtm().get(idAtm)));

        // проверю суммы в банкоматах после увеличения на 50р
        assertEquals(Long.valueOf(650 + 50), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(0)));
        assertEquals(Long.valueOf(1300 + 50), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(1)));
        assertEquals(Long.valueOf(1950 + 50), depAtm.getObserver().getMoneyInAtm().get(Long.valueOf(2)));
    }
}