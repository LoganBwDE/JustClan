package me.loganbwde.lst;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.loganbwde.Clan.main;

public class OnDamage implements Listener
{
    private main m;
    public OnDamage(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e)
    {
        try
        {
            if (Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("PvP.pvpinclan")) == false)
            {
                if (e.getEntity() instanceof Player)
                {
                    Player p = (Player) e.getEntity();
                    String pName = p.getName();
                    if ((e.getDamager() instanceof Player))
                    {
                        Player t = (Player) e.getDamager();
                        String tName = t.getName();
                        if ((m.getClanManager().getClan(pName) != null) && (m.getClanManager().getClan(tName) != null) && (m.getClanManager().getClan(pName).equalsIgnoreCase(m.getClanManager().getClan(tName))))
                        {
                            e.setCancelled(true);
                        }
                    }
                    if (e.getDamager() instanceof Arrow)
                    {
                        Player t = (Player) ((Arrow) e.getDamager()).getShooter();
                        String tName = t.getName();
                        if ((m.getClanManager().getClan(pName) != null) && (m.getClanManager().getClan(tName) != null) && (m.getClanManager().getClan(pName).equalsIgnoreCase(m.getClanManager().getClan(tName))))
                        {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
        catch (ClassCastException ex)
        {}
    }
}
