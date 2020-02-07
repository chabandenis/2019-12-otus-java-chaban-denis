package ru.chaban.domain;

import ru.chaban.service.Log;

public class TestLoggingImpl implements TestLogging {

    @Log
    public void calculation(int param) {
        System.out.println("param: " + param);
    }
}
