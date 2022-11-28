package org.example.service;

import org.example.service.menupoints.MainMenuService;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuServiceTest {

    private static MainMenuService mainMenuService;
    private static TransmittedData transmittedData;

    @BeforeAll
    public static void setUpAll() throws Exception {
        mainMenuService = new MainMenuService();
        transmittedData = new TransmittedData(0);
    }

    @Test
    void processCommandStart_CommandNotStart_ErrorMessage() throws Exception {
        SendMessage message = mainMenuService.processCommandStart("wrong_command", transmittedData);

        String expected = DialogStringsStorage.CommandStartError;
        String actual = message.getText();

        assertEquals(expected, actual);
    }
}