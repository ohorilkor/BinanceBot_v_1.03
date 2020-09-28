package com.company;

import org.telegram.telegrambots.ApiContextInitializer;

public class Main {

    private static final int PRIORITY_FOR_SENDER = 1;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot binanceBot = new Bot("Kravziv_Crypto_v1_02_bot","1377586865:AAFBrgy15GhYqweIIKHWhF_tkiSWe5aZ9_o");

        MessageSender messageSender = new MessageSender(binanceBot);

        binanceBot.botConnect();
        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();
    }
}
