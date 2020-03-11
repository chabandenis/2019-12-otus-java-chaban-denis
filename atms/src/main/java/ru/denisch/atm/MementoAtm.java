package ru.denisch.atm;

public class MementoAtm {
    private ComplexType4MementoAtm ct = new ComplexType4MementoAtm();

    public MementoAtm() {
    }

    public MementoAtm(ComplexType4MementoAtm ct) {
        this.ct = ct;
    }

    public ComplexType4MementoAtm getCt() {
        return ct;
    }

    public void setCt(ComplexType4MementoAtm ct) {
        this.ct = ct;
    }

    // клонирование
    public MementoAtm myClone() {
        MementoAtm mementoAtm = new MementoAtm();
        mementoAtm.ct = this.ct.myClone();
        return mementoAtm;
    }
}
