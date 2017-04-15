package me.loganbwde.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.loganbwde.Clan.main;

public class MessagesManager
{
    private main m;
    private FileManager fm;

    public MessagesManager(main main)
    {
        m = main;
        fm = m.getFileManager();
    }

    private String checkText(String text)
    {
        if (Boolean.parseBoolean(fm.getConfigEntrys().get("Basic.formatting")) == true)
        {
            if (text.contains("ae"))
            {
                text = text.replace("ae", "�");
            }
            if (text.contains("oe"))
            {
                text = text.replace("oe", "�");
            }
            if (text.contains("ue"))
            {
                text = text.replace("ue", "�");
            }

            if (text.contains("Ae"))
            {
                text = text.replace("Ae", "�");
            }
            if (text.contains("Oe"))
            {
                text = text.replace("Oe", "�");
            }
            if (text.contains("Ue"))
            {
                text = text.replace("Ue", "�");
            }

            if (text.contains("%sz%"))
            {
                text = text.replace("%sz%", "�");
            }
        }
        return text;
    }

    public void sendMessage(CommandSender p, String text)
    {
        String prefix = fm.getConfigEntrys().get("Basic.prefix");
        text = checkText(text);
        text = prefix + " " + text;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }
    
    public void sendMessageClan(CommandSender p,String text)
    {
        String prefix = fm.getConfigEntrys().get("Basic.prefixClan");
        text = checkText(text);
        text = prefix + " " + text;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public void sendMessageNoPrefix(CommandSender p, String text)
    {
        text = checkText(text);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }
}
