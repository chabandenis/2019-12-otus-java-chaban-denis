package ru.denisch;

import org.junit.jupiter.api.Test;
import ru.denisch.atm.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmImplTest {
    @Test
    void loadBills() {
        Atm atm = new AtmImpl(1);

        List<Bill> bills50 = new ArrayList<>();
        bills50.add(new BillImpl("r50 n001", CurTypeImpl.RUR50));
        bills50.add(new BillImpl("r50 n002", CurTypeImpl.RUR50));
        bills50.add(new BillImpl("r50 n003", CurTypeImpl.RUR50));

        List<Bill> bills100 = new ArrayList<>();
        bills100.add(new BillImpl("r100 n001", CurTypeImpl.RUR100));
        bills100.add(new BillImpl("r100 n002", CurTypeImpl.RUR100));
        bills100.add(new BillImpl("r100 n003", CurTypeImpl.RUR100));

        List<Bill> bills500 = new ArrayList<>();
        bills500.add(new BillImpl("r500 n001", CurTypeImpl.RUR500));
        bills500.add(new BillImpl("r500 n002", CurTypeImpl.RUR500));
        bills500.add(new BillImpl("r500 n003", CurTypeImpl.RUR500));

        try {
            atm.loadCassettes(bills50).loadCassettes(bills100).loadCassettes(bills500);
        } catch (AtmException e) {
            assertEquals(1, 2);
        }

        System.out.println("1 " + atm.status().size());

        for (Bill bill : atm.status()) {
            System.out.println(bill.toString());
        }

        System.out.println("2 " + atm.status().size());

        assertEquals(atm.status().get(0).getSerNumber(), "r50 n001");
        assertEquals(atm.status().get(1).getSerNumber(), "r50 n002");
        assertEquals(atm.status().get(2).getSerNumber(), "r50 n003");
        assertEquals(atm.status().get(3).getSerNumber(), "r100 n001");
        assertEquals(atm.status().get(4).getSerNumber(), "r100 n002");
        assertEquals(atm.status().get(5).getSerNumber(), "r100 n003");
        assertEquals(atm.status().get(6).getSerNumber(), "r500 n001");
        assertEquals(atm.status().get(7).getSerNumber(), "r500 n002");
        assertEquals(atm.status().get(8).getSerNumber(), "r500 n003");

        // пробуем передать больше чем есть
        System.out.println("загружаем больше чем есть");
        List<Bill> billsCnt10 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            billsCnt10.add(new BillImpl("over0" + i, CurTypeImpl.RUR50));
        }

        try {
            atm.addMoney(billsCnt10);
            assertEquals(1, 2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Касета переполнена");
        }
    }

    @Test
    void addMoney() throws AtmException {

        AtmImpl atmImpl = new AtmImpl(1);

        List<Bill> bills50 = new ArrayList<>();
        bills50.add(new BillImpl("r50 n001", CurTypeImpl.RUR50));

        List<Bill> bills100 = new ArrayList<>();
        bills100.add(new BillImpl("r100 n001", CurTypeImpl.RUR100));

        List<Bill> bills500 = new ArrayList<>();
        bills500.add(new BillImpl("r500 n001", CurTypeImpl.RUR500));

        System.out.println("Состояние пустого банкомата");
        System.out.println(atmImpl.toString());
        System.out.println();

        // первоначальная загрузка банкомата
        atmImpl.loadCassettes(bills500).loadCassettes(bills100).loadCassettes(bills50);

        System.out.println("Состояние после первоначальной загрузки банкомата");
        System.out.println(atmImpl.toString());
        System.out.println();

        List<Bill> tmp = new ArrayList<>();
        tmp.add(new BillImpl("my 50 001", CurTypeImpl.RUR50));
        tmp.add(new BillImpl("my 50 002", CurTypeImpl.RUR50));
        tmp.add(new BillImpl("my 500 003", CurTypeImpl.RUR500));

        // загружаю в банкомат курюры
        atmImpl.addMoney(tmp);

        System.out.println("Состояние после загрузки купюры");
        System.out.println(atmImpl.toString());
        System.out.println();

        // снять пытаюсь те же купюры в обратном порядке
        try {
            List<Bill> tmpList;
            tmpList = atmImpl.getMoney(50);

            assertEquals(tmpList.get(0).getSerNumber(), "my 50 002");

            System.out.println("Состояние после списания 50р");
            System.out.println(atmImpl.toString());
            System.out.println();

            tmpList = atmImpl.getMoney(1000);

            System.out.println("Состояние после списания 1000р");
            System.out.println(atmImpl.toString());
            System.out.println();

            assertEquals(tmpList.get(0).getSerNumber(), "my 500 003");
            assertEquals(tmpList.get(1).getSerNumber(), "r500 n001");

            tmpList = atmImpl.getMoney(150);

            System.out.println("Состояние после списания 150р");
            System.out.println(atmImpl.toString());
            System.out.println();

            assertEquals(tmpList.get(0).getSerNumber(), "r100 n001");
            assertEquals(tmpList.get(1).getSerNumber(), "my 50 001");
        } catch (AtmException e) {
            // в данном алгоритме ошбки должны отсутствовать
            assertEquals(1, 2);
        }

        // снимем меньше чем нужно
        // остаток 50 рублей
        // пробуем списать 40
        System.out.println("Пробуем списать 40р");
        try {
            List<Bill> tmpList;
            tmpList = atmImpl.getMoney(40);

            System.out.println("Состояние после списания 40р");
            System.out.println(atmImpl.toString());
            System.out.println();

            // должно возникнуть исключение о нехватке денег
            assertEquals(1, 2);
        } catch (AtmException e) {
            System.out.println(e.getMessage());
            // исключение, все верно
            assertEquals(e.getMessage(), "Подходящие купюры отсутствуют в банкомате");
        }

        // снимем больше чем нужно
        // остаток 50 рублей
        // пробуем списать 60
        System.out.println("Пробуем списать 60р");
        try {
            List<Bill> tmpList;
            tmpList = atmImpl.getMoney(60);

            System.out.println("Состояние после списания 60р");
            System.out.println(atmImpl.toString());
            System.out.println();

            // должно возникнуть исключение о нехватке денег
            assertEquals(1, 2);
        } catch (AtmException e) {
            System.out.println(e.getMessage());
            // исключение, все верно
            assertEquals(e.getMessage(), "Запрашиваемая сумма больше остатка в банкомате");
        }
    }
}