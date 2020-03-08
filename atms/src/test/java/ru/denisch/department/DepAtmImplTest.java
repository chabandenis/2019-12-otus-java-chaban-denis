package ru.denisch.department;

import org.junit.jupiter.api.Test;
import ru.denisch.atm.AtmException;
import ru.denisch.atm.AtmImpl;
import ru.denisch.atm.BillImpl;
import ru.denisch.atm.CurTypeImpl;

import java.util.ArrayList;
import java.util.List;

class DepAtmImplTest {

    @Test
    void department() throws AtmException {
        DepAtm depAtm = new DepAtmImpl();

        for (int i = 1; i <= 3; i++) {

            List<BillImpl> bills50 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills50.add(new BillImpl("r50 n0" + i + j, CurTypeImpl.RUR50));
            }

            List<BillImpl> bills100 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills100.add(new BillImpl("r100 n0" + i + j, CurTypeImpl.RUR100));
            }

            List<BillImpl> bills500 = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                bills500.add(new BillImpl("r500 n0" + i + j, CurTypeImpl.RUR500));
            }

            // доабавить банкомат в департамент
            depAtm.addAtm(new AtmImpl(i).loadCassetes(bills50).loadCassetes(bills100).loadCassetes(bills500));
        }
    }
}