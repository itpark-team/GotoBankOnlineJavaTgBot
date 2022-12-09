package org.example.api;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CbrApiWorkerTest {

    @Test
    void getJsonCurrentExchangeRate_ReturnJson() throws IOException, InterruptedException {
        //подготовка
        CbrApiWorker cbrApiWorker = new CbrApiWorker();

        //тестирование
        String actualJson = cbrApiWorker.getJsonCurrentExchangeRate();

        //проверка
        assertThat(actualJson).isNotEmpty();
    }
}