package ru.chaban.domain;

import ru.chaban.service.Log;

interface TestLogging {

    @Log
    void calculation(int param);


}
