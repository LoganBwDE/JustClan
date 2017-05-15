package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdNext
{
    private main m;

    public CmdNext(main main)
    {
        m = main;
    }

    public void menu(Player p)
    {
        for(int i = 0;i <= 20;i++)
        {
            if(m.getFileManager().getMessageEntrys().get("Menu.page2." + i) != null)
            {
                m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.page2." + i));
            }
        }
    }
}
