package com.ceydavardar.discordBot.listeners;

import com.golub.golubBot.Bot;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Welcome extends ListenerAdapter {

    private final Bot bot;
    public Welcome(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        TextChannel channel = event.getGuild().getChannelById(TextChannel.class, bot.getConfigHandler().getConfigContainer().welcomeChannelId);

        if (channel == null) {

            System.out.println("The channel ID specified in config.yml is not leading to a valid channel.");
            return;

        }

        channel.sendMessage(bot.getConfigHandler().getConfigContainer().welcomeMessage.replace("%member%", event.getMember().getAsMention())).queue();

    }

}
