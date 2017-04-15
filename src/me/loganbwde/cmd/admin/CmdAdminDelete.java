package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminDelete
{
    private main m;
    public CmdAdminDelete(main main)
    {
        m = main;
    }
    
    public void delete(Player p,String cl)
    {
        if (m.getClanManager().ClanExists(cl))
        {
            m.getClanManager().remClan(cl, m.getClanManager().getClanOwner(cl));
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.remclan"));
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clannotexist"));
        }
    }
}
