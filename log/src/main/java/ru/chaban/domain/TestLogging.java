package ru.chaban.domain;

import ru.chaban.service.Log;

interface TestLogging {

    @Log
    void calculation1(int param001);


    void calculation2(int param002);

    @Log
    void calculation3(int param003);


    void calculation4(int param004);

    @Log
    void calculation5(int param005);
}
