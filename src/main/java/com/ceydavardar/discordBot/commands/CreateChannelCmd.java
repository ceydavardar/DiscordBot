package com.ceydavardar.discordBot.commands;

import com.golub.golubBot.Bot;
import com.golub.golubBot.utils.Utils;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Objects;

public class CreateChannelCmd extends ListenerAdapter {

    private final Bot bot;
    public CreateChannelCmd(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) return;

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (!args[0].equals("-createchannel")) return;

        if (!Utils.hasRole(Objects.requireNonNull(event.getMember()), bot.getConfigHandler().getConfigContainer().adminRole)) {
            event.getChannel().sendMessage("You don't have permission to use this.").queue();
            return;
        }

        if (args.length < 3) {

            event.getChannel().sendMessage("You didn't use the command right, here is the right way: -createchannel <category-name> <channel-name>").queue();
            return;

        }

        List<Category> categories = event.getGuild().getCategoriesByName(args[1], true);

        if (categories.isEmpty()) {
            event.getChannel().sendMessage("This category could not be found.").queue();
            return;
        }

        Category category = categories.get(0);

        category.createTextChannel(args[2]).queue(channel -> {

            event.getChannel().sendMessage("Channel "+channel.getAsMention() + " has been created.").queue();

        });

    }

}
