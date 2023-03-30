package com.ceydavardar.discordBot.commands;

import com.golub.golubBot.Bot;
import com.golub.golubBot.utils.Utils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class KickCmd extends ListenerAdapter {

    private final Bot bot;
    public KickCmd(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) return;

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equals("-kick")) {

            if (!Utils.hasRole(Objects.requireNonNull(event.getMember()), bot.getConfigHandler().getConfigContainer().adminRole)) {
                event.getChannel().sendMessage("You can not use this command.").queue();
                return;
            }

            if (args.length < 2) {

                event.getChannel().sendMessage("You didn't use this command right. -kick <@member> is the right way.").queue();
                return;

            }

            if (event.getMessage().getMentions().getMembers().isEmpty()) {

                event.getChannel().sendMessage("You need to mention someone.").queue();
                return;

            }

            Member targetMember = event.getMessage().getMentions().getMembers().get(0);

            if (targetMember == null) {

                event.getChannel().sendMessage("This is not a valid member.").queue();
                return;

            }

            event.getChannel().sendMessage("Member, named "+targetMember.getAsMention()+" is kicked from server.").queue();

            targetMember.kick().queue();

        }

    }

}
