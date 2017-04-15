package me.loganbwde.lst;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.loganbwde.Clan.main;

public class OnInvClose implements Listener
{
    private main m;
    public OnInvClose(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e)
    {
        if (m.inedit.containsKey(e.getPlayer().getName()) && !m.matedit.contains(e.getPlayer().getName()) && !m.amountedit.contains(e.getPlayer().getName()) && !m.nameedit.contains(e.getPlayer().getName()) && !m.enchedit.contains(e.getPlayer().getName()) && !m.costedit.contains(e.getPlayer().getName()))
        {
            m.inedit.remove(e.getPlayer().getName());
        }
    }
}
