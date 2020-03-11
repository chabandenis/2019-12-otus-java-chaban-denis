package ru.denisch.atm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CaretakerMementoAtm {
    private MementoAtm mementoAtm;

    public MementoAtm getMementoAtm() {
        return mementoAtm;
    }

    public CaretakerMementoAtm setMementoAtm(MementoAtm mementoAtm) {
        this.mementoAtm = new MementoAtm();

        ComplexType4MementoAtm complexType4MementoAtm = new ComplexType4MementoAtm();

        List<Cassette> cassettes = new ArrayList<>();
        for (Cassette cassette : mementoAtm.getCt().getCassette()) {
            Cassette cassetteTemp = new CassetteImpl();
            cassetteTemp.setQ(((ArrayDeque<Bill>) cassette.getQ()).clone());
            cassetteTemp.setNominal(cassette.getNominal());
            cassettes.add(cassetteTemp);
        }
        complexType4MementoAtm.setCassettes(cassettes);

        complexType4MementoAtm.setCassetteForMoney(new HashMap<>(mementoAtm.getCt().getCassetteForMoney()));
        complexType4MementoAtm.setCntInCassette(new HashMap<>(mementoAtm.getCt().getCntInCassette()));

        this.mementoAtm.setCt(complexType4MementoAtm);

        return this;
    }
}
