package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdSethome
{
    private main m;
    public CmdSethome(main main)
    {
        m = main;
    }
    
    public void sethome(Player p)
    {
        m.getClanManager().setClanHome(p.getName(), p.getLocation());
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.sethome"));
    }
}
