package me.loganbwde.lst;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.loganbwde.Clan.main;

public class OnKill implements Listener
{
    private main m;
    public OnKill(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        Player p2 = p.getKiller();

        if (m.inwars.containsKey(p.getName()) && m.inwars.containsKey(p2.getName()))
        {
            m.killedwars.put(p.getName(), m.inwars.get(p.getName()));
            if (m.getWarsManager().checkWarsOver(p2.getName(), p.getName()))
            {
                String clan1 = m.getClanManager().getClan(p.getName());
                String clan2 = m.getClanManager().getClan(p2.getName());

                ArrayList<String> member1 = m.getClanManager().getMember(clan1);
                ArrayList<String> member2 = m.getClanManager().getMember(clan2);

                for (int i = 0; i < member1.size(); i++)
                {
                    if (Bukkit.getOnlinePlayers().contains(member1.get(i)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(member1.get(i))))
                    {
                        Location loc1 = m.getWarsManager().getEndLocation(m.inwars.get(member1.get(i)));
                        try
                        {
                            Bukkit.getPlayer(member1.get(i)).teleport(loc1);
                        }
                        catch (NullPointerException exe)
                        {
                        }
                        if (m.Team1.getEntries().contains(member1.get(i)))
                        {
                            m.Team1.removeEntry(member1.get(i));
                        }
                        else
                        {
                            if (m.Team2.getEntries().contains(member1.get(i)))
                            {
                                m.Team2.removeEntry(member1.get(i));
                            }
                        }
                        m.inwars.remove(member1.get(i));
                    }
                }
                for (int i = 0; i < member2.size(); i++)
                {
                    if (Bukkit.getOnlinePlayers().contains(member2.get(i)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(member2.get(i))))
                    {
                        Location loc2 = m.getWarsManager().getEndLocation(m.inwars.get(member2.get(i)));
                        try
                        {
                            Bukkit.getPlayer(member2.get(i)).teleport(loc2);
                        }
                        catch (NullPointerException exe)
                        {
                        }

                        m.inwars.remove(member2.get(i));
                        if (m.Team1.getEntries().contains(member2.get(i)))
                        {
                            m.Team1.removeEntry(member2.get(i));
                        }
                        else
                        {
                            if (m.Team2.getEntries().contains(member2.get(i)))
                            {
                                m.Team2.removeEntry(member2.get(i));
                            }
                        }
                    }
                }

                if (Bukkit.getOnlinePlayers().contains(m.getClanManager().getClanOwner(clan1)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(m.getClanManager().getClanOwner(clan1))))
                {
                    Location loc1 = m.getWarsManager().getEndLocation(m.inwars.get(m.getClanManager().getClanOwner(clan1)));
                    Bukkit.getPlayer(m.getClanManager().getClanOwner(clan1)).teleport(loc1);
                    m.inwars.remove(m.getClanManager().getClanOwner(clan1));
                    if (m.Team1.getEntries().contains(m.getClanManager().getClanOwner(clan1)))
                    {
                        m.Team1.removeEntry(m.getClanManager().getClanOwner(clan1));
                    }
                    else
                    {
                        if (m.Team2.getEntries().contains(m.getClanManager().getClanOwner(clan1)))
                        {
                            m.Team2.removeEntry(m.getClanManager().getClanOwner(clan1));
                        }
                    }
                    m.getWarsManager().getWars().remove(clan1);
                    m.getWarsManager().getLocation().remove(clan1);
                }

                if (Bukkit.getOnlinePlayers().contains(m.getClanManager().getClanOwner(clan2)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(m.getClanManager().getClanOwner(clan2))))
                {
                    Location loc2 = m.getWarsManager().getEndLocation(m.inwars.get(m.getClanManager().getClanOwner(clan2)));
                    Bukkit.getPlayer(m.getClanManager().getClanOwner(clan2)).teleport(loc2);
                    m.inwars.remove(m.getClanManager().getClanOwner(clan2));
                    if (m.Team1.getEntries().contains(m.getClanManager().getClanOwner(clan2)))
                    {
                        m.Team1.removeEntry(m.getClanManager().getClanOwner(clan2));
                    }
                    else
                    {
                        if (m.Team2.getEntries().contains(m.getClanManager().getClanOwner(clan2)))
                        {
                            m.Team2.removeEntry(m.getClanManager().getClanOwner(clan2));
                        }
                    }
                    m.getWarsManager().getWars().remove(clan2);
                    m.getWarsManager().getLocation().remove(clan2);
                }
            }
        }
        else
        {
            if (p2 != null)
            {
                m.getClanManager().addKill(p2.getName());
                m.getClanManager().addLevel(p, p2);
                m.getClanManager().addDeath(p.getName());
            }
        }
    }
}
