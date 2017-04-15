package me.loganbwde.lst;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.loganbwde.Clan.main;

public class OnPlace implements Listener
{
    private main m;
    public OnPlace(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent e)
    {
        if (m.inwars.containsKey(e.getPlayer().getName()))
        {
            e.setCancelled(true);
        }
    }
}
