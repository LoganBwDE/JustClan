package me.loganbwde.cmd.normal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdDeny
{
    private main m;
    public CmdDeny(main main)
    {
        m = main;
    }
    
    public void deny(Player p, String[] args)
    {
        String name = p.getName();
        if (m.getClanManager().getInvites().get(name) != null)
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.denyclan"));
            String pown = m.getClanManager().getClanOwner(args[1]);
            Player po = Bukkit.getServer().getPlayer(pown);
            m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.denyclan2").replace("%player%", name));
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notinv"));
        }
    }
}
