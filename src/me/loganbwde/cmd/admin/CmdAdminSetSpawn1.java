package me.loganbwde.cmd.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminSetSpawn1
{
    private main m;
    private boolean sucess;
    public CmdAdminSetSpawn1(main main)
    {
        m = main;
    }
    
    public void setspawn(Player p,String[] args)
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
                m.statement.executeUpdate("UPDATE " + m.tablename3 + " SET spawn1 = '" + l + "' WHERE name='" + args[2] + "';");
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.spawn1set"));
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
