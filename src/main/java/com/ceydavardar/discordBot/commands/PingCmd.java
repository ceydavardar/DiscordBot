package com.ceydavardar.discordBot.commands;

import com.golub.golubBot.Bot;
import com.golub.golubBot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PingCmd extends ListenerAdapter {

    private final Bot bot;
    public PingCmd(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) return;

        String message = event.getMessage().getContentRaw();

        if (!Utils.hasRole(Objects.requireNonNull(event.getMember()), bot.getConfigHandler().getConfigContainer().adminRole))

        if (message.equals("-ping")) {

            event.getMessage().reply("Ping...").queue(success -> {

                long ping = event.getMessage().getTimeCreated().until(success.getTimeCreated(), ChronoUnit.MILLIS);
                success.editMessage("Ping: "+ping+" ms & Websocket Ping: "+event.getJDA().getGatewayPing()+" ms").queue();

            });

        }

    }

}
