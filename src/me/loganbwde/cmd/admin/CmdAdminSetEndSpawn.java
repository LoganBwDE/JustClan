package me.loganbwde.cmd.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminSetEndSpawn
{
    private main m;
    private boolean sucess;
    public CmdAdminSetEndSpawn(main main)
    {
        m = main;
    }
    
    public void setspawn(Player p,String [] args)
    {
        Location loc = p.getLocation();
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + args[2] + "';");
            rs.next();
            if (!rs.getString("name").equalsIgnoreCase(args[2]))
            {
                sucess = false;
            }
            else
            {
                String l = "";
                l += loc.getWorld().getName();
                l += ",";
                l += String.valueOf(loc.getBlockX());
                l += ",";
                l += String.valueOf(loc.getBlockY());
                l += ",";
                l += String.valueOf(loc.getBlockZ());
                m.statement.executeUpdate("UPDATE " + m.tablename3 + " SET end = '" + l + "' WHERE name='" + args[2] + "';");
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.endspawnset"));
                sucess = true;
            }
        }
        catch (SQLException e)
        {
        }
        if (sucess == false)
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.warsnotexist"));
        }
    }
}
