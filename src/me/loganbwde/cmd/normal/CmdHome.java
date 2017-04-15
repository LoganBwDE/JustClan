package me.loganbwde.cmd.normal;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdHome
{
    private main m;
    public CmdHome(main main)
    {
        m = main;
    }
    
    public void home(Player p)
    {
        String name = p.getName();
        Location loc = null;
        try
        {
            loc = m.getClanManager().getClanHome(name);
        }
        catch (ArrayIndexOutOfBoundsException exeption)
        {
        }

        if (loc != null)
        {
            p.teleport(loc);
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.home"));
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclanhome"));
        }
    }
}
