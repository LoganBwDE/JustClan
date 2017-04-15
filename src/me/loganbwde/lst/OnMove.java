package me.loganbwde.lst;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.loganbwde.Clan.main;

public class OnMove implements Listener
{
    private main m;
    public OnMove(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        if (m.inwars.containsKey(e.getPlayer().getName()))
        {
            String wars = m.inwars.get(e.getPlayer().getName());
            String wall = m.getWarsManager().checkPlayer(e.getPlayer(), wars);
            if (!wall.isEmpty())
            {
                if (wall.contains("x1"))
                {
                    Location loc = e.getPlayer().getLocation();
                    loc.setX(e.getPlayer().getLocation().getBlockX() - 0.1);
                    e.getPlayer().teleport(loc);
                }
                if (wall.contains("x2"))
                {
                    Location loc = e.getPlayer().getLocation();
                    loc.setX(e.getPlayer().getLocation().getX() + 0.1);
                    e.getPlayer().teleport(loc);
                }
                if (wall.contains("z1"))
                {
                    Location loc = e.getPlayer().getLocation();
                    loc.setZ(e.getPlayer().getLocation().getZ() + 0.1);
                    e.getPlayer().teleport(loc);
                }
                if (wall.contains("z2"))
                {
                    Location loc = e.getPlayer().getLocation();
                    loc.setZ(e.getPlayer().getLocation().getZ() - 0.1);
                    e.getPlayer().teleport(loc);
                }
            }
        }
    }
}
