package me.loganbwde.cmd.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminList
{
    private main m;
    public CmdAdminList(main main)
    {
        m = main;
    }
    
    public void list(Player p)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename);
            int i = 0;
            m.getMessagesManager().sendMessageNoPrefix(p, "&3[======================&cClans&3=====================]");
            while (rs.next() == true)
            {
                i++;
                m.getMessagesManager().sendMessageNoPrefix(p, "&c" + i + ": &aName: " + rs.getString("name") + " &6| &aTag: " + rs.getString("tag") + " &6| &aOwner: " + rs.getString("owner"));
            }
            m.getMessagesManager().sendMessageNoPrefix(p, "&3[===================================================]");
        }
        catch (SQLException e)
        {}
    }
}
