package dev.panasovsky.telegram.composer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TelegramBotApplication {

    public TelegramBotApplication() {}

    public static void main(final String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}