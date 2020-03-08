package ru.denisch.atm;

public class MementoAtm {
    private ComplexType4MementoAtm ct;

    public MementoAtm(ComplexType4MementoAtm ct) {
        this.ct = ct;
    }

    public ComplexType4MementoAtm getCt() {
        return ct;
    }

    public void setCt(ComplexType4MementoAtm ct) {
        this.ct = ct;
    }
}
