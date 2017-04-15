package me.loganbwde.lst;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.loganbwde.Clan.main;

public class OnBreak implements Listener
{
    private main m;
    public OnBreak(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if (m.inwars.containsKey(e.getPlayer().getName()))
        {
            e.setCancelled(true);
        }
    }
}
