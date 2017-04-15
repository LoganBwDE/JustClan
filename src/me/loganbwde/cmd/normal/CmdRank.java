package me.loganbwde.cmd.normal;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdRank
{
    private main m;
    public CmdRank(main main)
    {
        m = main;
    }
    
    public void rank(Player p)
    {
        String name = p.getName();
        ArrayList<String> rank = m.getClanManager().getRank(p);
        String rankName = rank.get(0);
        String nextrank = rank.get(1);
        String rankPoints = rank.get(2);
        String rankKills = rank.get(3);
        String rankColorNow = rank.get(4);
        String rankColorNext = rank.get(5);
        try
        {
            if (!rankColorNow.equalsIgnoreCase("0"))
            {
                rankName = rankColorNow + rankName;
                rankName = ChatColor.translateAlternateColorCodes('&', rankName);
            }
        }
        catch (NullPointerException e)
        {
        }
        if (!rankColorNext.equalsIgnoreCase("0"))
        {
            nextrank = rankColorNext + nextrank;
            nextrank = ChatColor.translateAlternateColorCodes('&', nextrank);
        }
        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rank").replace("%clan%", m.getClanManager().getClan(name)).replace("%rank%", rankName).replace("%points%", rankPoints).replace("%rank%", rankName).replace("%kills%", rankKills).replace("%nextrank%", nextrank));
    }
}
