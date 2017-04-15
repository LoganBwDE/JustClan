package me.loganbwde.cmd.normal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdLeave
{
    private main m;

    public CmdLeave(main main)
    {
        m = main;
    }
    
    public void leave(Player p)
    {
        String name = p.getName();
        String cl = m.getClanManager().getClan(name);
        m.getClanManager().leaveClan(m.getClanManager().getClan(name), name);
        String pown = m.getClanManager().getClanOwner(cl);
        Player po = Bukkit.getServer().getPlayer(pown);
        m.getMessagesManager().sendMessage(po, (m.getFileManager().getMessageEntrys().get("Messages.leaveclan2")).replace("%player%", name));
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.leaveclan"));
    }
}
