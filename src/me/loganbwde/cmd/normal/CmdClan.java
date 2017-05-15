package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdClan
{
    private main m;

    public CmdClan(main main)
    {
        m = main;
    }

    public void menu(Player p)
    {
        for(int i = 0;i <= 20;i++)
        {
            if(m.getFileManager().getMessageEntrys().get("Menu.page1." + i) != null)
            {
                m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.page1." + i));
            }
        }
    }
}
