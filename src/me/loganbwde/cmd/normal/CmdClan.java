package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdClan
{
    private main m;

    public CmdClan(main main)
    {
        m = main;
    }

    public void menu(Player p)
    {
        m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.header"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan info : " + m.getFileManager().getMessageEntrys().get("Menu.claninfo"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan create <Name> <Tag> : " + m.getFileManager().getMessageEntrys().get("Menu.clancreate"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan delete : " + m.getFileManager().getMessageEntrys().get("Menu.clandelete"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan leave : " + m.getFileManager().getMessageEntrys().get("Menu.clanleave"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan invite [revoke] <Player>: " + m.getFileManager().getMessageEntrys().get("Menu.claninvite"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan accept <Clan> : " + m.getFileManager().getMessageEntrys().get("Menu.clanaccept"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan deny <Clan> : " + m.getFileManager().getMessageEntrys().get("Menu.clandeny"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan kick <Player> : " + m.getFileManager().getMessageEntrys().get("Menu.clankick"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan pay <Points> : " + m.getFileManager().getMessageEntrys().get("Menu.clanpay"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan rankup : " + m.getFileManager().getMessageEntrys().get("Menu.clanrank"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&6/clan next: " + m.getFileManager().getMessageEntrys().get("Menu.secondpage"));
        m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.footer"));
    }
}
