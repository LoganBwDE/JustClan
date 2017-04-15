package me.loganbwde.cmd.normal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAccept
{
    private main m;
    public CmdAccept(main main)
    {
        m = main;
    }
    
    @SuppressWarnings({ "deprecation", "null"})
    public void accept(Player p,String arg)
    {
        String[] args = null;
        args[1] = arg;
        String name = p.getName();
        String paysystem = m.getFileManager().getConfigEntrys().get("Basic.paysystem");
        if (m.getClanManager().getInvites().get(name) != null)
        {
            boolean accept = false;
            if (!Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("ClanJoin.clanJoinCosts")))
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.joinedclan"));
                for (int i = 0; i < m.getClanManager().getMember(m.getClanManager().getClan(name)).size(); i++)
                {
                    Player pl = Bukkit.getServer().getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(name)).get(i));
                    if (pl != null)
                    {
                        m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", pl.getName()));
                    }
                }
                String pown = m.getClanManager().getClanOwner(args[1]);
                Player po = Bukkit.getServer().getPlayer(pown);
                m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", name));
                m.getClanManager().joinClan(args[1], p.getName());
            }
            else
            {
                if (paysystem.toLowerCase().equalsIgnoreCase("item"))
                {
                    Material mat = Material.getMaterial(m.getFileManager().getConfigEntrys().get("ClanJoin.JoinItem").toUpperCase());
                    int amount = 0;
                    for(int i = 0;i < p.getInventory().getSize();i++)
                    {
                        if(p.getInventory().getItem(i).getType() == mat)
                        {
                            amount = amount + p.getInventory().getItem(i).getAmount();
                        }
                    }
                    if(amount <= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanJoin.JoinAmount")))
                    {
                        for(int i = 0;i < p.getInventory().getSize();i++)
                        {
                            if(p.getInventory().getItem(i).getType() == mat)
                            {
                                if(amount <= p.getInventory().getItem(i).getAmount())
                                {
                                    amount = p.getInventory().getItem(i).getAmount() - amount;
                                    p.getInventory().getItem(i).setAmount(amount);
                                    p.updateInventory();
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.joinedclan"));        
                                    for (int j = 0; j < m.getClanManager().getMember(m.getClanManager().getClan(name)).size(); j++)
                                    {
                                        Player pl = Bukkit.getServer().getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(name)).get(j));
                                        if (pl != null)
                                        {
                                            m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", pl.getName()));
                                        }
                                    }
                                    String pown = m.getClanManager().getClanOwner(args[1]);
                                    Player po = Bukkit.getServer().getPlayer(pown);
                                    m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", name));
                                    m.getClanManager().joinClan(args[1], name);
                                    accept = true;
                                }
                                else
                                {
                                    amount = amount - p.getInventory().getItem(i).getAmount();
                                    p.getInventory().remove(i);
                                    p.updateInventory();
                                }
                            }
                        }
                    }
                    if (accept == false)
                    {
                        if (paysystem.equalsIgnoreCase("item"))
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notitemsjoin"));        
                        }
                    }
                }
                else
                {
                    if (paysystem.toLowerCase().equalsIgnoreCase("money"))
                    {
                        if (Bukkit.getPluginManager().getPlugin("Vault") != null)
                        {
                            if (m.getEcononmy().getBalance(p.getName()) >= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanJoin.clanJoinMoneyNeed")))
                            {
                                m.getEcononmy().withdrawPlayer(p.getName(), Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanJoin.clanJoinMoneyNeed")));
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.joinedclan"));        
                                for (int j = 0; j < m.getClanManager().getMember(m.getClanManager().getClan(name)).size(); j++)
                                {
                                    Player pl = Bukkit.getServer().getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(name)).get(j));
                                    if (pl != null)
                                    {
                                        m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", pl.getName()));
                                    }
                                }
                                String pown = m.getClanManager().getClanOwner(args[1]);
                                Player po = Bukkit.getServer().getPlayer(pown);
                                m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.playerjoined").replace("%player%", name));
                                m.getClanManager().joinClan(args[1], name);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughmoney"));        
                            }
                        }
                    }
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notinv"));        
        }
    }
}
