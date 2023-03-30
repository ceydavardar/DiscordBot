package com.ceydavardar.discordBot;

import com.golub.golubBot.configuration.config.ConfigHandler;

public class Bot {

    private final ConfigHandler configHandler;

    public Bot(ConfigHandler configHandler) {
        this.configHandler = configHandler;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
