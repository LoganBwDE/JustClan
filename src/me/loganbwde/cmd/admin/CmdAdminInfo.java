package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminInfo
{
    private main m;
    public CmdAdminInfo(main main)
    {
        m = main;
    }
    
    public void menu(Player p,String cl)
    {
        if (m.getClanManager().ClanExists(cl))
        {
            String pName = m.getClanManager().getClanOwner(cl);

            m.getMessagesManager().sendMessageNoPrefix(p, "&3[====================&c" + "Admin Info" + "&3===================]");
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanname") + ": &c" + cl);
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clantag") + ": &c" + m.getClanManager().getClanTag(pName));
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanowner") + ": &c" + m.getClanManager().getClanOwner(m.getClanManager().getClan(pName)));
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanmember") + ": &c" + m.getClanManager().getMember(m.getClanManager().getClan(pName)) + " [" + m.getClanManager().getClanSize(pName) + "]");
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clankd") + ": &c" + m.getFileManager().getMessageEntrys().get("ClanMenu.clankills") + " " + m.getClanManager().getClanKill(pName) + "  /  " + m.getFileManager().getMessageEntrys().get("ClanMenu.clandeaths") + " " + m.getClanManager().getClanDeath(pName) + " (" + m.getClanManager().getClanKD(pName) + ")");
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanlevel") + ": &c" + m.getClanManager().getClanLevel(pName) + " [" + m.getClanManager().getNeedLevel(pName) + "]");
            m.getMessagesManager().sendMessageNoPrefix(p, "&3" + m.getFileManager().getMessageEntrys().get("ClanMenu.clanranking") + ": &c" + m.getClanManager().getClanRank(pName));
            m.getMessagesManager().sendMessageNoPrefix(p, "&3[===============================================]");
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notexist"));
        }
    }
}
