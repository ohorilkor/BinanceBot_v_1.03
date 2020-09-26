package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    final int RECONNECT_PAUSE =10000;

    public ArrayList<Long> chatIds = new ArrayList<Long>();

    public SendMessage message = new SendMessage();

    String userName;
    String token;

    public Bot(String userName, String token){
        this.userName = userName;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(!chatIds.contains(update.getMessage().getChat().getId())) {
            chatIds.add(update.getMessage().getChat().getId());
        }
        message.setChatId(update.getMessage().getChatId());
        message.setText("Welcome! You are logged in. Wait for notifications");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public SendMessage getMessage(){
        return message;
    }

    public ArrayList<Long> getChatIds(){
        return chatIds;
    }

    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
