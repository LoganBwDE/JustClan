package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdPay
{
    private main m;
    public CmdPay(main main)
    {
        m = main;
    }
    
    public void pay(Player p,String[] args)
    {
        String name = p.getName();
        int level = 0;
        try
        {
            level = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            m.getMessagesManager().sendMessage(p, "&cSyntax: /clan pay <Points>");
            return;
        }
        if (level <= m.getClanManager().getPlayerLevel(name))
        {
            m.getClanManager().ClanPay(level, m.getClanManager().getClan(name));
            m.getClanManager().removePoints(name, level);
            String l = String.valueOf(level);
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.lvlpayd").replace("%points%", l));
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughpoints"));
        }
    }
}
