package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminSetPoints
{
    private main m;
    public CmdAdminSetPoints(main main)
    {
        m = main;
    }
    
    public void setpoints(Player p, String[] args)
    {
        if (m.getClanManager().getAllClans().contains(args[2]))
        {
            int points = Integer.parseInt(args[3]);
            if (points < 0)
            {
                m.getClanManager().removeClanPoints(args[2], points);
                String l = String.valueOf(points);
                String n = l.substring(1);
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.pointsremoved").replace("%points%", n).replace("%clan%", args[2]));
            }
            else
            {
                if (points > 0)
                {
                    m.getClanManager().addClanPoints(args[2], points);
                    String l = String.valueOf(points);
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.pointsadded").replace("%points%", l).replace("%clan%", args[2]));
                }
            }
        }
        else
        {
            int points = Integer.parseInt(args[3]);
            if (m.getClanManager().PlayerExists(args[2]))
            {
                if (points > 0)
                {
                    m.getClanManager().addPoints(args[2], points);
                    String l = String.valueOf(points);
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.pointsaddedplayer").replace("%points%", l).replace("%player%", args[2])); 
                }
                else
                {
                    if (points < 0)
                    {
                        m.getClanManager().removePoints(args[2], points);
                        String l = String.valueOf(points);
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.pointsremovedplayer").replace("%points%", l).replace("%player%", args[2])); 
                    }
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clannotexist"));
            }
        }
    }
}
