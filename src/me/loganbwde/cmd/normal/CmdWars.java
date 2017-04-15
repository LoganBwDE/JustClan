package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdWars
{
    private main m;
    public CmdWars(main main)
    {
        m = main;
    }
    
    public void wars(Player p,String[] args)
    {
        String name = p.getName();
        if (m.getWarsManager().getWars().containsKey(m.getClanManager().getClan(name)))
        {
            if (args[1].equalsIgnoreCase("accept"))
            {
                m.getWarsManager().acceptWars(m.getClanManager().getClan(name));
            }

            if (args[1].equalsIgnoreCase("deny"))
            {
                m.getWarsManager().denyWars(m.getClanManager().getClan(name));
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.warsnotinvited"));
        }
    }
}
