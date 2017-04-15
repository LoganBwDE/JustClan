package me.loganbwde.cmd.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminRankup
{
    private main m;
    public CmdAdminRankup(main main)
    {
        m = main;
    }
    
    public void rankup(Player p,String[] args)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + args[2] + "';");
            rs.next();
            int rank = rs.getInt("rank");
            rank++;
            m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + args[2] + "';");
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clanadminrankup").replace("%clan%", args[2]));
        }
        catch (SQLException e)
        {}
    }
}
