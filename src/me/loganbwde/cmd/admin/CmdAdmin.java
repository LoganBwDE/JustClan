package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdmin
{
    private main m;
    public CmdAdmin(main main)
    {
        m = main;
    }
    
    public void menu(Player p)
    {
        for(int i = 0;i <= 20;i++)
        {
            if(m.getFileManager().getMessageEntrys().get("Menu.admin." + i) != null)
            {
                m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.admin." + i));
            }
        }
    }
}
