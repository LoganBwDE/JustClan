package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdReload
{
    private main m;
    public CmdReload(main main)
    {
        m = main;
    }
    
    public void reload(Player p)
    {
        m.getFileManager().loadFiles();
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rl"));
    }
}
