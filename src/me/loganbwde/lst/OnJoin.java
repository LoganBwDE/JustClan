package me.loganbwde.lst;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.loganbwde.Clan.main;

public class OnJoin implements Listener
{
    private main m;
    public OnJoin(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @SuppressWarnings("unused")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + e.getPlayer().getName() + "';");
            rs.next();
            String name = rs.getString("name");
        }
        catch (SQLException ex)
        {
            m.statement.executeUpdate("INSERT INTO " + m.tablename2 + " (`name`, `uuid`, `clan`, `kills`, `deaths`, `points`, `rank`) VALUES ('" + e.getPlayer().getName() + "',  '" + e.getPlayer().getUniqueId().toString() + "', '" + "" + "', '" + "0" + "', '" + "0" + "', '" + "0" + "', '" + "" + "');");
        }

        if (m.matedit.contains(e.getPlayer().getName()) || m.amountedit.contains(e.getPlayer().getName()) || m.nameedit.contains(e.getPlayer().getName()) || m.enchedit.contains(e.getPlayer().getName()) || m.costedit.contains(e.getPlayer().getName()))
        {
            m.matedit.remove(e.getPlayer().getName());
            m.amountedit.remove(e.getPlayer().getName());
            m.nameedit.remove(e.getPlayer().getName());
            m.enchedit.remove(e.getPlayer().getName());
            m.costedit.remove(e.getPlayer().getName());
        }
    }
    
}