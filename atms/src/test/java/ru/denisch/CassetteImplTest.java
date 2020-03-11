package ru.denisch;

import org.junit.jupiter.api.Test;
import ru.denisch.atm.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CassetteImplTest {

    @Test
    void TestEnum() {
        CurType curType = CurTypeImpl.RUR100;
        System.out.println("_" + curType.getNominal());
    }

    @Test
    void putGet() {
        Cassette cassette = new CassetteImpl();
        List<Bill> bills = new ArrayList<>();

        bills.add(new BillImpl("001", CurTypeImpl.RUR50));
        bills.add(new BillImpl("002", CurTypeImpl.RUR50));
        bills.add(new BillImpl("003", CurTypeImpl.RUR50));

        try {
            cassette.put(bills);

            System.out.println(cassette.toString());

            HashSet d = new HashSet();

            assertEquals(cassette.get(1).get(0).getSerNumber(), "003");
            assertEquals(cassette.get(1).get(0).getSerNumber(), "002");
            assertEquals(cassette.get(1).get(0).getSerNumber(), "001");

        } catch (AtmException e) {
            assertEquals(1, 2);
        }
    }
}