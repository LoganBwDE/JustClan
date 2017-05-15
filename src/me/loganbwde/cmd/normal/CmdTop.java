package me.loganbwde.cmd.normal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdTop
{
    private main m;
    private HashMap<Integer,String> clantop;
    public CmdTop(main main)
    {
        m = main;
    }
    
    public void top(Player p)
    {
        clantop = new HashMap<>();
        String po = "";
        if (m.getFileManager().getConfigEntrys().get("Basic.lang").contentEquals("en"))
        {
            po = "Points: ";
        }
        else
        {
            if (m.getFileManager().getConfigEntrys().get("Basic.lang").contentEquals("de"))
            {
                po = "Punkte: ";
            }
        }
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " ORDER BY points DESC");
            int i = 0;
            m.getMessagesManager().sendMessageNoPrefix(p, "&3[======================&cClans&3=====================]");
            while (rs.next() == true)
            {
                if (i <= 5)
                {
                    i++;
                    clantop.put(i, rs.getString("owner"));
                }
            }
        }
        catch (SQLException e)
        {}
        String owner;
        for (int i = 1; i <= 5; i++)
        {
            if (clantop.get(i) != null)
            {
                owner = clantop.get(i);
                m.getMessagesManager().sendMessageNoPrefix(p, "&c" + i + ": &aName: " + m.getClanManager().getClanByOwner(owner) + " &6| &aTag: " + m.getClanManager().getClanTagByOwner(owner) + " &6| &aOwner: " + owner + " &6| &a" + po + m.getClanManager().getClanLevelByOwner(owner));
            }
        }
        m.getMessagesManager().sendMessageNoPrefix(p, "&3[===================================================]");
    }
}
