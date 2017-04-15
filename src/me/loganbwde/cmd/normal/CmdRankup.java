package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdRankup
{
    private main m;
    public CmdRankup(main main)
    {
        m = main;
    }
    
    public void rankup(Player p)
    {
        m.getClanManager().Rank(m.getClanManager().getClan(p.getName()), p.getName());
    }
}
