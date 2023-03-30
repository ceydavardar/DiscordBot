package com.ceydavardar.discordBot.commands;

import com.golub.golubBot.Bot;
import com.golub.golubBot.configuration.YamlConfiguration;
import com.golub.golubBot.configuration.config.ConfigHandler;
import com.golub.golubBot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.Objects;

public class BlacklistCmd extends ListenerAdapter {

    private final Bot bot;

    public BlacklistCmd(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) return;

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (!args[0].equals("-blacklistword")) return;

        if (!Utils.hasRole(Objects.requireNonNull(event.getMember()), bot.getConfigHandler().getConfigContainer().adminRole)) {
            event.getChannel().sendMessage("You don't have permission.").queue();
            return;
        }

        if (args.length == 1) {
            event.getChannel().sendMessage("You need to give me a word for me to put it into the blacklist.").queue();
            return;
        }

        String word = args[1];

        ConfigHandler config = bot.getConfigHandler();

        if (config.getConfigContainer().blacklistedWords.contains(word)) {

            event.getChannel().sendMessage("This word is already in the blacklist.").queue();
            return;

        }

        config.getConfigContainer().blacklistedWords.add(word);

        config.getConfiguration().set("word-blacklist", config.getConfigContainer().blacklistedWords);

        try {
            YamlConfiguration.getProvider(YamlConfiguration.class).save(config.getConfiguration(), config.getConfigResource().getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        event.getChannel().sendMessage("The word has been added to the word blacklist.").queue();

    }

}
