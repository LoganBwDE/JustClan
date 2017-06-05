package me.loganbwde.cmd.normal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdCreate
{
    private main m;
    public CmdCreate(main main)
    {
        m = main;
    }
    
    @SuppressWarnings("deprecation")
    public void create(Player p,String name, String tag)
    {
        boolean created = false;
        String paysystem = m.getFileManager().getConfigEntrys().get("Basic.paysystem");
        if(!m.getClanManager().HaveClan(name))
        {
            if (!Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateCosts")))
            {
                m.getClanManager().ClanCreate(name, p.getName(), tag);
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                created = true;
            }
            else
            {
                if (paysystem.toLowerCase().equalsIgnoreCase("item"))
                {
                    Material mat = Material.getMaterial(m.getFileManager().getConfigEntrys().get("ClanCreate.CreateItem").toUpperCase());
                    int amount = 0;
                    for(int i = 0;i < p.getInventory().getSize();i++)
                    {
                        try
                        {
                            if(p.getInventory().getItem(i).getType() == mat)
                            {
                                amount = amount + p.getInventory().getItem(i).getAmount();
                            }
                        }
                        catch(NullPointerException ex)
                        {}
                    }
                    if(amount <= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.CreateAmount")))
                    {
                        for(int i = 0;i < p.getInventory().getSize();i++)
                        {
                            try
                            {
                                if(p.getInventory().getItem(i).getType() == mat)
                                {
                                    if(amount <= p.getInventory().getItem(i).getAmount())
                                    {
                                        amount = p.getInventory().getItem(i).getAmount() - amount;
                                        p.getInventory().getItem(i).setAmount(amount);
                                        p.updateInventory();
                                        m.getClanManager().ClanCreate(name, p.getName(), tag);
                                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                                        created = true;
                                    }
                                    else
                                    {
                                        amount = amount - p.getInventory().getItem(i).getAmount();
                                        p.getInventory().remove(i);
                                        p.updateInventory();
                                    }
                                }
                            }
                            catch(NullPointerException ex)
                            {}
                        }
                    }
                }
                else
                {
                    if (paysystem.toLowerCase().equalsIgnoreCase("money"))
                    {
                        if (Bukkit.getPluginManager().getPlugin("Vault") != null)
                        {
                            if (m.getEcononmy().getBalance(p.getName()) >= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateMoneyNeed")))
                            {
                                m.getEcononmy().withdrawPlayer(p.getName(), Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateMoneyNeed")));
                                m.getClanManager().ClanCreate(name, p.getName(), tag);
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                                created = true;
                            }
                        }
                    }
                }
            }
            if (created == false)
            {
                if (paysystem.equalsIgnoreCase("money"))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughmoney"));
                }
                if (paysystem.equalsIgnoreCase("item"))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notitemscreate"));
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyhaveclan"));
        }
    }
    
    @SuppressWarnings("deprecation")
    public void create(Player p,String name)
    {
        boolean created = false;
        String paysystem = m.getFileManager().getConfigEntrys().get("Basic.paysystem");
        if(!m.getClanManager().HaveClan(name))
        {
            if (!Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateCosts")))
            {
                m.getClanManager().ClanCreate(name, p.getName(), "");
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                created = true;
            }
            else
            {
                if (paysystem.toLowerCase().equalsIgnoreCase("item"))
                {
                    Material mat = Material.getMaterial(m.getFileManager().getConfigEntrys().get("ClanCreate.CreateItem").toUpperCase());
                    int amount = 0;
                    for(int i = 0;i < p.getInventory().getSize();i++)
                    {
                        if(p.getInventory().getItem(i).getType() == mat)
                        {
                            amount = amount + p.getInventory().getItem(i).getAmount();
                        }
                    }
                    if(amount <= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.CreateAmount")))
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
                                    m.getClanManager().ClanCreate(name, p.getName(), "");
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                                    created = true;
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
                }
                else
                {
                    if (paysystem.toLowerCase().equalsIgnoreCase("money"))
                    {
                        if (Bukkit.getPluginManager().getPlugin("Vault") != null)
                        {
                            if (m.getEcononmy().getBalance(p.getName()) >= Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateMoneyNeed")))
                            {
                                m.getEcononmy().withdrawPlayer(p.getName(), Integer.parseInt(m.getFileManager().getConfigEntrys().get("ClanCreate.clanCreateMoneyNeed")));
                                m.getClanManager().ClanCreate(name, p.getName(), "");
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.crclan"));
                                created = true;
                            }
                        }
                    }
                }
            }
            if (created == false)
            {
                if (paysystem.equalsIgnoreCase("money"))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughmoney"));
                }
                if (paysystem.equalsIgnoreCase("item"))
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notitemscreate"));
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyhaveclan"));
        }
    }
}
