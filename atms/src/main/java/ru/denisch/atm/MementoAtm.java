package ru.denisch.atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MementoAtm {
    // в банкомате есть фиксированное количество касет
    private List<CassetteImpl> cassetteImpls = new ArrayList<>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<CassetteImpl, Long> cntInCassete = new HashMap<>();


    // по купюре определим нужную касету
    private Map<Integer, CassetteImpl> casseteForMoney = new HashMap<>();

    public List<CassetteImpl> getCassetteImpls() {
        return cassetteImpls;
    }

    public void setCassetteImpls(List<CassetteImpl> cassetteImpls) {
        this.cassetteImpls = cassetteImpls;
    }

    public Map<CassetteImpl, Long> getCntInCassete() {
        return cntInCassete;
    }

    public void setCntInCassete(Map<CassetteImpl, Long> cntInCassete) {
        this.cntInCassete = cntInCassete;
    }

    public Map<Integer, CassetteImpl> getCasseteForMoney() {
        return casseteForMoney;
    }

    public void setCasseteForMoney(Map<Integer, CassetteImpl> casseteForMoney) {
        this.casseteForMoney = casseteForMoney;
    }
}
