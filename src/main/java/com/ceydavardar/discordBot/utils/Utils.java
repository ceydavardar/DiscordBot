package com.ceydavardar.discordBot.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Utils {

    public static boolean hasRole(Member member, String roleName) {

        for (Role role : member.getRoles()) {

            if (role.getName().equals(roleName)) return true;

        }

        return false;

    }

}
