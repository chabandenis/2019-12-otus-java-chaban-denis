package ru.chaban.examples;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    public List<String> list = new ArrayList();

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
