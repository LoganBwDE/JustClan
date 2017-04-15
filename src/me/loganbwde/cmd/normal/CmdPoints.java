package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdPoints
{
    private main m;
    public CmdPoints(main main)
    {
        m = main;
    }
    
    public void points(Player p)
    {
        int points = m.getClanManager().getPlayerLevel(p.getName());
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.yourpoints").replace("%points%", String.valueOf(points)));
    }
}
