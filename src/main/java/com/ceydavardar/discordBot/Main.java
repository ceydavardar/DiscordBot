package com.ceydavardar.discordBot;

import com.golub.golubBot.commands.*;
import com.golub.golubBot.configuration.Resource;
import com.golub.golubBot.configuration.config.ConfigHandler;
import com.golub.golubBot.listeners.BlacklistedWords;
import com.golub.golubBot.listeners.Welcome;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    public static void main(String[] args) {

        Resource configResource = new Resource("config.yml");

        Bot bot = new Bot(new ConfigHandler(configResource));

        JDABuilder jdaBuilder = JDABuilder.createDefault(bot.getConfigHandler().getConfigContainer().token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);

        jdaBuilder.addEventListeners(new PingCmd(bot));
        jdaBuilder.addEventListeners(new KickCmd(bot));
        jdaBuilder.addEventListeners(new CreateChannelCmd(bot));
        jdaBuilder.addEventListeners(new BlacklistCmd(bot));
        jdaBuilder.addEventListeners(new BlacklistedWords(bot));
        jdaBuilder.addEventListeners(new HeadsTailsCmd());
        jdaBuilder.addEventListeners(new Welcome(bot));

        JDA jda = jdaBuilder.build();

    }

}
