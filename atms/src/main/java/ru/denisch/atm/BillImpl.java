package ru.denisch.atm;

// купюра
public class BillImpl implements Bill {

    private String serNumber; // serial number of money
    private CurType curType;

    public BillImpl(String serNumber, CurTypeImpl curType) {
        this.serNumber = serNumber;
        this.curType = curType;
    }

    public String getSerNumber() {
        return serNumber;
    }

    public CurType getCurType() {
        return curType;
    }

    @Override
    public String toString() {
        return "sr=" + serNumber + "; " + "nom=" + curType.getNominal();
    }
}
