package me.loganbwde.cmd.normal;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdStartWars
{
    private main m;
    public CmdStartWars(main main)
    {
        m = main;
    }
    
    public void startWars(Player p,String[] args)
    {
        String name = p.getName();
        ArrayList<String> locations = m.getWarsManager().getWarsLocations();
        boolean islocation = false;
        boolean invitesended = false;
        for (int i = 0; i < locations.size(); i++)
        {
            if (args[1].equalsIgnoreCase(locations.get(i)))
            {
                islocation = true;
            }
        }

        if (islocation == true)
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                if (invitesended == false)
                {
                    if (m.getClanManager().HaveClan(player.getName()))
                    {
                        if (m.getClanManager().getClan(name) != args[2])
                        {
                            if (m.getClanManager().getAllClans().contains(args[2]))
                            {
                                m.getWarsManager().sendWarsInvite(m.getClanManager().getClan(name), args[2], args[1]);
                                invitesended = true;
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notexist"));
                            }
                        }
                        else
                        {
                        }
                    }
                    else
                    {
                    }
                }
            }
        }
        else
        {
            String l = "";
            for (int i = 0; i < m.getWarsManager().getWarsLocations().size(); i++)
            {
                if (i == 0)
                {
                    l = m.getWarsManager().getWarsLocations().get(i);
                }
                else
                {
                    l = l + ", " + m.getWarsManager().getWarsLocations().get(i);
                }
            }
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.locationnotexist").replace("%locations%", l));
        }
    }
}
