package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdKick
{
    private main m;
    public CmdKick(main main)
    {
        m = main;
    }
    
    public void kick(Player p, String[] args)
    {
        String name = p.getName();
        if (m.getClanManager().isOwner(name) == true)
        {
            if (args[1] != name)
            {
                if (!m.getClanManager().getMember(m.getClanManager().getClan(name)).contains(args[1]))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notinyourclan").replace("%player%", args[1]));
                }
                else
                {
                    m.getClanManager().ClanKick(m.getClanManager().getClan(name), args[1]);
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.waskicked").replace("%player%", args[1]));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.cantkickself"));
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
        }
    }
}
