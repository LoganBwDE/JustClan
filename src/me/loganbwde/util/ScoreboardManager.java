package me.loganbwde.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.loganbwde.Clan.main;

public class ScoreboardManager
{
    private main m;
    private Scoreboard sb;
    private Team Team1;
    private Team Team2;
    private int time;
    private boolean firsttime;
    private int min;
    public ScoreboardManager(main main)
    {
        m = main;
        time = 0;
        min = 0;
        firsttime = false;
    }
    
    @SuppressWarnings("deprecation")
    public void updateSB()
    {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o;
        o = sb.registerNewObjective("ClanWars", "dummy");

        Team1 = (sb.getTeam("Team1") == null ? sb.registerNewTeam("Team1") : sb.getTeam("Team1"));
        Team2 = (sb.getTeam("Team2") == null ? sb.registerNewTeam("Team2") : sb.getTeam("Team2"));

        Team1.setPrefix(ChatColor.translateAlternateColorCodes('&', "&c"));
        Team2.setPrefix(ChatColor.translateAlternateColorCodes('&', "&2"));

        o.setDisplayName("§aClanWars");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        String wars = "n.a.";
        String team = "n.a.";
        String enemy = "n.a.";

        for (Player p : Bukkit.getOnlinePlayers())
        {
            if (m.inwars.containsKey(p))
            {
                wars = m.inwars.get(p);
                if (Team1.getPlayers().contains(p.getName()))
                {
                    // Bukkit.broadcastMessage("In Team1 ist "+ p.getName());
                }
                if (Team2.getPlayers().contains(p.getName()))
                {
                    // Bukkit.broadcastMessage("In Team2 ist "+ p.getName());
                }
            }
            if (m.getClanManager().HaveClan(p.getName()))
            {
                team = m.getClanManager().getClan(p.getName());
            }
        }

        String players = String.valueOf(Bukkit.getOnlinePlayers().size());
        time++;
        Score one = null;

        if (time == 60)
        {
            min++;
            time = 0;
            if (min > 9)
            {
                one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b" + min + " : 0" + time));
            }
            else
            {
                one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b0" + min + " : 0" + time));
            }
        }
        else
        {
            if (time < 10)
            {
                if (min == 0)
                {
                    one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b00 : 0" + time));
                }
                else
                {
                    if (min <= 9)
                    {
                        one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b0" + min + " : 0" + time));
                    }
                    else
                    {
                        if (min > 9)
                        {
                            one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b" + min + " : 0" + time));
                        }
                    }
                }
            }
            else
            {
                if (min == 0)
                {
                    one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b00 : " + time));
                }
                else
                {
                    if (min <= 9)
                    {
                        one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b0" + min + " : " + time));
                    }
                    else
                    {
                        if (min > 9)
                        {
                            one = o.getScore(Bukkit.getOfflinePlayer("§aZeit§6: §b" + min + " : " + time));
                        }
                    }
                }
            }
        }
        Score five = o.getScore(Bukkit.getOfflinePlayer("§aMap§6: §b" + wars));
        Score four = o.getScore(Bukkit.getOfflinePlayer("§aGegner§6: §b" + enemy));
        Score three = o.getScore(Bukkit.getOfflinePlayer("§aDein Clan§6:§b " + team));
        Score two = o.getScore(Bukkit.getOfflinePlayer("§aSpieler§6: §b" + players));

        five.setScore(5);
        four.setScore(4);
        three.setScore(3);
        two.setScore(2);
        one.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.setScoreboard(sb);
        }
    }

    public void TabAkt()
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            if (m.inwars.containsKey(p.getName()))
            {
                if (firsttime == false)
                {
                    p.setScoreboard(sb);
                }
            }
        }
        firsttime = true;
    }

    public void setScoreboard(Scoreboard sbnew)
    {
        sb = sbnew;
    }

    public Scoreboard getScoreboard()
    {
        return sb;
    }
}
