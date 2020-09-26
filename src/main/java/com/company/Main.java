package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Main {

    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot binanceBot = new Bot("Kravziv_Crypto_v1_02_bot","1377586865:AAFBrgy15GhYqweIIKHWhF_tkiSWe5aZ9_o");

       // MessageReciever messageReciever = new MessageReciever(binanceBot);
        MessageSender messageSender = new MessageSender(binanceBot);

        binanceBot.botConnect();

      /*  Thread receiver = new Thread(messageReciever);
        receiver.setDaemon(true);
        receiver.setName("MsgReciever");
        receiver.setPriority(PRIORITY_FOR_RECEIVER);
        receiver.start();
*/
        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();
    }
}
