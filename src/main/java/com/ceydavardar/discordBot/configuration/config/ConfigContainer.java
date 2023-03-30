package com.ceydavardar.discordBot.configuration.config;

import com.golub.golubBot.configuration.Configuration;

import java.util.List;

public class ConfigContainer {

    public String token;
    public List<String> blacklistedWords;
    public String adminRole;
    public String welcomeChannelId;
    public String welcomeMessage;

    public ConfigContainer(Configuration config) {

        this.welcomeMessage = config.getString("welcome.message");
        this.welcomeChannelId = config.getString("welcome.channel-id");
        this.adminRole = config.getString("admin-role");
        this.token = config.getString("token");
        this.blacklistedWords = config.getStringList("word-blacklist");

    }

}
