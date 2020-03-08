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

        List<CassetteImpl> cassettes = new ArrayList<>();
        for (CassetteImpl cassette : mementoAtm.getCt().getCassetteImpls()) {
            CassetteImpl cassetteTemp = new CassetteImpl();
            cassetteTemp.setQ(((ArrayDeque<BillImpl>) cassette.getQ()).clone());
            cassetteTemp.setNominal(cassette.getNominal());
            cassettes.add(cassetteTemp);
        }
        complexType4MementoAtm.setCassetteImpls(cassettes);

        complexType4MementoAtm.setCasseteForMoney(new HashMap<>(mementoAtm.getCt().getCasseteForMoney()));
        complexType4MementoAtm.setCntInCassete(new HashMap<>(mementoAtm.getCt().getCntInCassete()));

        this.mementoAtm.setCt(complexType4MementoAtm);

        return this;
    }
}
