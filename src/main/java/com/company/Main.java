package com.company;

import org.telegram.telegrambots.ApiContextInitializer;

public class Main {

    private static final int PRIORITY_FOR_SENDER = 1;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot binanceBot = new Bot("KravzivBinanceBTC_bot","1033141491:AAE4PWe57wppU4wpXCbuSUU-4tO1IDsd_nM");

        MessageSender messageSender = new MessageSender(binanceBot);

        binanceBot.botConnect();
        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();
    }
}
