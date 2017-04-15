package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdInfo
{
    private main m;
    public CmdInfo(main main)
    {
        m = main;
    }
    
    public void menu(Player p)
    {
        String pName = p.getName();
        m.getMessagesManager().sendMessageNoPrefix(p, "&3[====================&r" + m.getFileManager().getMessageEntrys().get("ClanMenu.yourclan") + "&3===================]");
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanname") + ": &c" + m.getClanManager().getClan(pName));
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clantag") + ": &c" + m.getClanManager().getClanTag(pName));
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanowner") + ": &c" + m.getClanManager().getClanOwner(m.getClanManager().getClan(pName)));
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanmember") + ": &c" + m.getClanManager().getMember(m.getClanManager().getClan(pName)) + " [" + m.getClanManager().getClanSize(pName) + "]");
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clankd") + ": &c" + m.getFileManager().getMessageEntrys().get("ClanMenu.clankills") + " " + m.getClanManager().getClanKill(pName) + "  /  " + m.getFileManager().getMessageEntrys().get("ClanMenu.clandeaths") + " " + m.getClanManager().getClanDeath(pName) + " (" + m.getClanManager().getClanKD(pName) + ")");
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanlevel") + ": &c" + m.getClanManager().getClanLevel(pName) + " [" + m.getClanManager().getNeedLevel(pName) + "]");
        m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanranking") + ": &c" + m.getClanManager().getClanRank(pName));
        m.getMessagesManager().sendMessageNoPrefix(p, "&3[===============================================]");
    }
}
