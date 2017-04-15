package me.loganbwde.lst;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.loganbwde.Clan.main;

public class OnRespawn implements Listener
{
    private main m;
    public OnRespawn(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        if (m.killedwars.containsKey(e.getPlayer().getName()))
        {
            Location loc = m.getWarsManager().getEndLocation(m.killedwars.get(e.getPlayer().getName()));
            m.killedwars.remove(e.getPlayer().getName());
            e.setRespawnLocation(loc);
        }
    }
}
