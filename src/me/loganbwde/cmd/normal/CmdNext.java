package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdNext
{
    private main m;

    public CmdNext(main main)
    {
        m = main;
    }

    public void menu(Player p)
    {
        m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.header"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan home : " + m.getFileManager().getMessageEntrys().get("Menu.clanhome"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan sethome : " + m.getFileManager().getMessageEntrys().get("Menu.clansethome"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan rank : " + m.getFileManager().getMessageEntrys().get("Menu.clanrank"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan applyrank [accept|deny] [player]: " + m.getFileManager().getMessageEntrys().get("Menu.applyrank"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan wars <accept|deny> <Clan> : " + m.getFileManager().getMessageEntrys().get("Menu.clanwarsdecision"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan startwars <Location> <Clan> : " + m.getFileManager().getMessageEntrys().get("Menu.clanstartwars"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan points : " + m.getFileManager().getMessageEntrys().get("Menu.clanpoints"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan shop : " + m.getFileManager().getMessageEntrys().get("Menu.clanshop"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan top : " + m.getFileManager().getMessageEntrys().get("Menu.clantop"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan chat : " + m.getFileManager().getMessageEntrys().get("Menu.clanchat"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6#<" + m.getFileManager().getMessageEntrys().get("Menu.clanmessage1") + "&6> : " + m.getFileManager().getMessageEntrys().get("Menu.clanmessage2"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan: " + m.getFileManager().getMessageEntrys().get("Menu.firstpage"));
        m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.footer"));
    }

}
