package com.ceydavardar.discordBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class HeadsTailsCmd extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) return;

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equals("-heads")) {

            Random random = new Random();

            double number = random.nextDouble();

            if (number > 0.5) {
                event.getChannel().sendMessage("You won! It was Heads!").queue();
            } else {
                event.getChannel().sendMessage("You lost! It was Tails!").queue();
            }

        } else if (args[0].equals("-tails")) {

            Random random = new Random();

            double number = random.nextDouble();

            if (number < 0.5) {
                event.getChannel().sendMessage("You won! It was Tails!").queue();
            } else {
                event.getChannel().sendMessage("You lost! It was Heads!").queue();
            }

        }

    }

}
