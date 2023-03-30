package com.ceydavardar.discordBot.listeners;

import com.golub.golubBot.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BlacklistedWords extends ListenerAdapter {

    private final Bot bot;
    public BlacklistedWords(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.isFromGuild()) return;

        if (event.getAuthor().isBot()) return;

        for (String word : bot.getConfigHandler().getConfigContainer().blacklistedWords) {

            if (event.getMessage().getContentRaw().contains(word)) {
                event.getMessage().delete().queue();
            }

        }

    }

}
