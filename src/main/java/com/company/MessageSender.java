package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class MessageSender implements Runnable {


    private Bot bot;
    boolean text_setted = false;
    public SendMessage message = new SendMessage();
    int chack = 0;

    public MessageSender(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        while (true) {
            text_setted = Volume_calculation.Volume_cal(message);
            chack++;
            if (text_setted) {
                send();
                text_setted = false;
            }
            if (chack > 45) {
                message.setText("I am allive");
                send();
                chack = 0;
            }
        }
    }

    private void send() {
        ArrayList<Long> chatIds = bot.getChatIds();
        for(int i =0; i<chatIds.size(); i++){
            message.setChatId(chatIds.get(i));
            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}