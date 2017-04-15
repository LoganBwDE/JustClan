package me.loganbwde.cmd.normal;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdApplyRank
{
    private HashMap<String,String> sendedrankup;
    private main m;
    public CmdApplyRank(main main)
    {
        m = main;
    }
    
    public void applyRankOwner(Player p,String[] args)
    {
        if (sendedrankup.containsKey(m.getClanManager().getClanByOwner(p.getName())))
        {
            if (args[1].equalsIgnoreCase("accept"))
            {
                if (!args[2].equalsIgnoreCase(p.getName()))
                {
                    String player = sendedrankup.get(m.getClanManager().getClanByOwner(p.getName()));
                    if (Bukkit.getPlayer(player).isOnline() && player.equalsIgnoreCase(args[2]))
                    {
                        sendedrankup.remove(m.getClanManager().getClanByOwner(p.getName()));
                        m.getClanManager().nextRank(Bukkit.getPlayer(player));
                        m.getMessagesManager().sendMessage(Bukkit.getPlayer(player), m.getFileManager().getMessageEntrys().get("Messages.rankupgrade"));
                    }
                    else
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notonline").replace("%player%", player));
                    }
                }
                else
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankyourself"));
                }
            }
            else
            {   
                if (args[1].equalsIgnoreCase("deny"))
                {
                    if (!args[2].equalsIgnoreCase(p.getName()))
                    {
                        String player = sendedrankup.get(m.getClanManager().getClanByOwner(p.getName()));
                        if (Bukkit.getPlayer(player).isOnline() && player.equalsIgnoreCase(args[2]))
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.ranknotaccepted"));
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notonline").replace("%player%", args[2]));
                        }
                    }
                    else
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankyourself"));
                    }
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.ranknorequests"));
        }
    }
    
    public void applyRankNormal(Player p)
    {
        try
        {
            Player owner = Bukkit.getPlayer(m.getClanManager().getClanOwner(m.getClanManager().getClan(p.getName())));
            if (owner.isOnline())
            {
                if (sendedrankup.containsValue(p.getName()))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankalreadyrequested"));
                }
                else
                {
                    ArrayList<String> playerrank = m.getClanManager().getRank(p);
                    int rankPoints = Integer.parseInt(playerrank.get(2));
                    int rankKills = Integer.parseInt(playerrank.get(3));
                    if (m.getClanManager().getPlayerLevel(p.getName()) >= rankPoints && m.getClanManager().getKill(p.getName()) >= rankKills)
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankalreadyrequested"));
                        sendedrankup.put(m.getClanManager().getClan(p.getName()), p.getName());
                    }
                    else
                    {
                        if (m.getClanManager().getPlayerLevel(p.getName()) < rankPoints)
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.ranknotenoughpoints"));
                        }
                        else
                        {
                            if (m.getClanManager().getKill(p.getName()) < rankKills)
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.ranknotenoughkills"));
                            }
                        }
                    }
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankownernotonline"));
            }
        }
        catch (NullPointerException exep)
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.error"));
        }
    }
}
