package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminKick
{
    private main m;
    public CmdAdminKick(main main)
    {
        m = main;
    }
    
    public void kick(Player p,String[] args)
    {
        if (m.getClanManager().ClanExists(args[2]))
        {
            m.getClanManager().ClanKick(args[2], args[3]);
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.waskicked").replace("%player%", args[3]));
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clannotexist"));
        }
    }
}
