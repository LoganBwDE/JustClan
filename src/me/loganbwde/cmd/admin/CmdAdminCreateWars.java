package me.loganbwde.cmd.admin;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminCreateWars
{
    private main m;
    public CmdAdminCreateWars(main main)
    {
        m = main;
    }
    
    public void create(Player p,String[] args)
    {
        try
        {
            m.statement.executeUpdate("INSERT INTO " + m.tablename3 + " (`name`, `location1`, `location2`, `spawn1`, `spawn2`) VALUES ('" + args[2] + "',  '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "');");
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clanwarscreated").replace("%wars%", args[2]));
        }
        catch (SQLException e)
        {
        }      
    }
}
