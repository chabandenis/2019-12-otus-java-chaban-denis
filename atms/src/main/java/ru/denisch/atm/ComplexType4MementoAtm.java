package ru.denisch.atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexType4MementoAtm {

    // в банкомате есть фиксированное количество касет
    private List<Cassette> cassettes = new ArrayList<Cassette>();

    // банкомат должен знать в какой касете сколько купюр
    // операции зачисления и списания должны актуализировать остаток
    // это не дублирование кода, дак как касета механическая штука
    private Map<Cassette, Long> cntInCassette = new HashMap<Cassette, Long>();
    // по купюре определим нужную касету
    private Map<Integer, Cassette> cassetteForMoney = new HashMap<Integer, Cassette>();

    public List<Cassette> getCassette() {
        return cassettes;
    }

    public void setCassettes(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public Map<Cassette, Long> getCntInCassette() {
        return cntInCassette;
    }

    public void setCntInCassette(Map<Cassette, Long> cntInCassette) {
        this.cntInCassette = cntInCassette;
    }

    public Map<Integer, Cassette> getCassetteForMoney() {
        return cassetteForMoney;
    }

    public void setCassetteForMoney(Map<Integer, Cassette> cassetteForMoney) {
        this.cassetteForMoney = cassetteForMoney;
    }
}
