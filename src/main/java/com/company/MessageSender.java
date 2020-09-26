package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MessageSender implements Runnable {


    private Bot bot;
    public Timer timer = new Timer();
    boolean text_setted = false;
    public SendMessage message = new SendMessage();

    public MessageSender(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                text_setted = Volume_calculation.Volume_cal(message);
                if(text_setted){
                    send();
                    text_setted = false;
                }
            }
        }, 0, 1*20);
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