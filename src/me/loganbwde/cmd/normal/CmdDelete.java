package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdDelete
{
    private main m;
    public CmdDelete(main main)
    {
        m = main;
    }
    
    public void delete(Player p)
    {
        String name = p.getName();
        m.getClanManager().remClan(m.getClanManager().getClan(name), name);
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.remclan"));
    }
}
